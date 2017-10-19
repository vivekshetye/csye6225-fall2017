#!/bin/bash    

#Mihir Patil,     001220443, patil.m@husky.neu.edu
#Vivek Shetye,    001237626, shetye.v@husky.neu.edu
#Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
#Atul Takekar,    001220479, takekar.a@husky.neu.edu


sh ./create_group.sh      

AMI_ID="ami-cd0f5cb6"
SECURITY_GROUP_NAME="csye6225-fall2017-webapp"

INSTANCE_ID=$(aws ec2 run-instances --image-id $AMI_ID --instance-type t2.micro --disable-api-termination --security-groups $SECURITY_GROUP_NAME --block-device-mappings "[{\"DeviceName\":\"/dev/sda1\",\"Ebs\":{\"VolumeSize\":16,\"VolumeType\":\"gp2\",\"DeleteOnTermination\":true}}]" --query 'Instances[0].InstanceId' --output text)

aws ec2 wait instance-running --instance-ids $INSTANCE_ID


PUBLIC_IP=$(aws ec2 describe-instances --instance-ids $INSTANCE_ID --query "Reservations[0].Instances[0].PublicIpAddress" --output text);

DOMAIN_NAME="ec2.csye6225-fall2017-patilm.me."

aws route53 change-resource-record-sets --hosted-zone-id Z31TTYNFTW06J1 --change-batch "{\"Comment\": \"DNS name for my instance.\", \"Changes\":[{\"Action\": \"UPSERT\", \"ResourceRecordSet\": { \"Name\": \""$DOMAIN_NAME"\", \"Type\": \"A\", \"TTL\": 60, \"ResourceRecords\": [{\"Value\": \""$PUBLIC_IP"\"}]}}]}"








