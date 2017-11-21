#Assignment no 4

Objective:

Create a CloudFormation stack that contains following resources:

1) Create security group for EC2 Instance
2) Configure security group for EC2 Instances
3) Launch EC2 Instance
4) Wait for EC2 instance to be in running state.
5) Retrieving instance’s public IP address.
6) Add/Update type A resource record set ec2.YOUR_DOMAIN_NAME.me in the Route 53 zone for your domain with the IP of the newly launched EC2 instance. TTL for the resource record set should be set to 60 seconds.
7) Create security group for RDS instance.
8) Configure security group for RDS instance.
9) Add Ingress rule to security group to allow traffic.
10) Create a DynamoDB table with provided configuration.
11) Create a S3 bucket with customized bucket name.
12) Create 2 subnets in existing vpc.
13) Create a DB subnet group.
14) Launch RDS instance.



 
#Getting Started

Install and setup AWS command line interface.

#Team Member Information

 * Mihir Patil- patil.m@husky.neu.edu
 * Vivek Shetye- shetye.v@husky.neu.edu
 * Pushkar Khedekar- khedekar.p@husky.neu.edu
 * Atul Takekar- takekar.a@husky.neu.edu
 
#Prerequisites 
 * AWS CLI
 * AWS account (Route53, IAM, CloudFormation, EC2)

 
#Installing
 A step by step series of examples that tell you have to get a environment running 

* Create group & user on IAM having a programatic access
* Install Python & AWS CLI
* Configure user using AWS CLI using 'aws configure' command by passing its secret key and access key values


#Executing scripts
* Run create-csye6225-cloudformation-stack.sh script from AWS CLI to achieve ojectives mentioned above. This script will need stack-template.json file present in cloudformation directory. 

