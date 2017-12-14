#!/bin/bash
# Creating first instance
gcloud compute instances create compute-1 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --zone us-east1-d --tags network-lb-tag --metadata startup-script="#! /bin/bash
sudo apt-get update
sudo apt-get install apache2 -y
sudo service apache2 restart
echo '<!doctype html><html><body><h1>www1</h1></body></html>' | tee /var/www/html/index.html
EOF"

# Creating second instance
gcloud compute instances create compute-2 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --zone us-east1-d --tags network-lb-tag --metadata startup-script="#! /bin/bash
sudo apt-get update
sudo apt-get install apache2 -y
sudo service apache2 restart
echo '<!doctype html><html><body><h1>www2</h1></body></html>' | tee /var/www/html/index.html
EOF"

# Creating third instance
gcloud compute instances create compute-3 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --zone us-east1-d --tags network-lb-tag --metadata startup-script="#! /bin/bash
sudo apt-get update
sudo apt-get install apache2 -y
sudo service apache2 restart
echo '<!doctype html><html><body><h1>www3</h1></body></html>' | tee /var/www/html/index.html
EOF"

# Create Firewall rules
gcloud compute firewall-rules create www-firewall-network-lb --target-tags network-lb-tag --allow tcp:80

# Create static ip for load balancer
gcloud compute addresses create network-lb-ip-1 --region us-east1

# Set health checker for instance group
gcloud compute http-health-checks create basic-check

# Create target pool for managed instance group
gcloud compute target-pools create www-pool --region us-east1 --http-health-check basic-check

# Append instance group to load balancer
gcloud compute target-pools add-instances www-pool --instances compute-1,compute-2,compute-3 --instances-zone us-east1-d

# Forwarding rule for load balancer
gcloud compute forwarding-rules create www-rule --region us-east1 --ports 80 --address network-lb-ip-1 --target-pool www-pool

#Create Bucket
gsutil mb -p "csye-6225-webapp" -c "regional" -l "us-east1" gs://"csye-6225-bucket"


#create bigtable
gcloud beta bigtable instances create instance-bigtable --cluster=csye6225-fall2017-final-cluster --cluster-zone=us-east1-b --description=test --cluster-num-nodes=3

#create rds instance
gcloud sql instances create sql-instance --tier=db-g1-small --region=us-east1
while true; do
InstanceStatus=`gcloud sql instances describe sql-instance | grep RUNNABLE`
echo $InstanceStatus
if [[ "$InstanceStatus" == "state: RUNNABLE" ]];
then
break
fi
done
gcloud sql databases create mySchema --instance=sql-instance
cloud sql users set-password root % --instance sql-instance --password=root_access
gcloud sql users create user1 % --instance sql-instance --password=rootPassword

#Getting Static IP for Load Balancer
TempStaticIP=`gcloud compute forwarding-rules describe www-rule --region us-east1 | grep IPAddress`
endIndex=`expr ${#TempStaticIP} - 1`
StaticIP=`echo $TempStaticIP | cut -c 12-$endIndex`

#Create a MANAGED ZONE
gcloud dns managed-zones create csye6225zone --dns-name csye6225-fall2017-khedekarp.me. --description zonedescription

#Starting Transaction
gcloud dns record-sets transaction start -z=csye6225zone

#Create Resource Record Set
gcloud dns record-sets transaction add --zone csye6225zone --name=csye6225-fall2017-khedekarp.me. --ttl=60 --type=A $StaticIP

#Executing a transaction
gcloud dns record-sets transaction execute -z=csye6225zone


#Create SNS Topic
gcloud beta pubsub topics create csye6225topic

#Create Google Cloud Function
#gcloud beta functions deploy helloWorld --stage-bucket csye6225-fall2017-gcp-final --trigger-http --trigger-topic=csye6225topic
gcloud beta functions deploy helloWorld --stage-bucket csye-6225-bucket --trigger-http
