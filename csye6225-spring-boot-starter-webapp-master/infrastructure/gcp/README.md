#Assignment no 6
This assignment demonstrates the basics of setting up google cloud platform.

#Getting Started
These instructions will get your VM instance for google cloud platform up and running.
#Team Member Information

 * Mihir Patil- patil.m@husky.neu.edu
 * Vivek Shetye- shetye.v@husky.neu.edu
 * Pushkar Khedekar- khedekar.p@husky.neu.edu
 * Atul Takekar- takekar.a@husky.neu.edu
 
 #Prerequisites 
 * google cloud compute account
 
 #Installing
 A step by step series of commands that tell you how to configure the google cloud sdk for managing VM instances 

*Create an environment variable for the correct distribution
``` bash
export CLOUD_SDK_REPO="cloud-sdk-$(lsb_release -c -s)"
```

*Add the Cloud SDK distribution URI as a package source
``` bash
echo "deb http://packages.cloud.google.com/apt $CLOUD_SDK_REPO main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
```

*Import the Google Cloud public key
``` bash
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
```

*Update and install the Cloud SDK
``` bash
sudo apt-get update && sudo apt-get install google-cloud-sdk
```

*Run gcloud init to get started
``` bash
gcloud init
```

* Create an instance 
``` bash 
gcloud compute instances create csye-6225-project --zone us-east1-d --machine-type f1-micro --project csye-6225-webapp
```


 
