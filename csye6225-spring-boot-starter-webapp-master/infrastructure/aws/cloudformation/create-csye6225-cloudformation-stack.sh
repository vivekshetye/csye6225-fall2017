#!/bin/bash



#Mihir Patil,     001220443, patil.m@husky.neu.edu
#Vivek Shetye,    001237626, shetye.v@husky.neu.edu
#Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
#Atul Takekar,    001220479, takekar.a@husky.neu.edu


echo "Please enter Stack Name"
read stackName
echo "Please enter Amazon Machine Image Id"
read imgId
echo "Please enter the VPC ID"
read VPCId
echo "Please enter the instance type"
read InstType
echo "Please enter the volume size"
read volumeSize
echo "Please enter the volume type"
read volumeType
echo "Please enter the public key name"
read keyName
echo "Please enter the hosted Zone Id"
read hostedZoneId
echo "Please enter the dns name"
read dnsName
echo "Please enter the RecordSet Type"
read recordSetType
echo "Please enter the RecordSeT Time to Live"
read recordSetTTL

aws cloudformation create-stack --stack-name $stackName --enable-termination-protection --template-body  file:///home/patilmihir/Assignment_3/csye6225-fall2017/csye6225-spring-boot-starter-webapp-master/infrastructure/aws/cloudformation/stack-template.json --parameters ParameterKey=ImgId,ParameterValue=$ImgId ParameterKey=VPCId,ParameterValue=$VPCId ParameterKey=InstType,ParameterValue=$InstType ParameterKey=volumeSize,ParameterValue=$volumeSize ParameterKey=volumeType,ParameterValue=$volumeType ParameterKey=keyName,ParameterValue=$keyName ParameterKey=hostedZoneId,ParameterValue=$hostedZoneId ParameterKey=dnsName,ParameterValue=$dnsName ParameterKey=recordSetType,ParameterValue=$recordSetType ParameterKey=recordSetTTL,ParameterValue=$recordSetTTL


#aws cloudformation create-stack --stack-name stack2 --template-body file:///home/patilmihir/Assignment_3/csye6225-fall2017/csye6225-spring-boot-starter-webapp-master/infrastructure/aws/cloudformation/stack-template.json --parameters ParameterKey=imgId,ParameterValue=ami-cd0f5cb6 ParameterKey=vpcId,ParameterValue=vpc-2433d45c ParameterKey=keyName,ParameterValue=id_rsa ParameterKey=InstType,ParameterValue=t2.micro ParameterKey=volumeSize,ParameterValue=16 ParameterKey=volumeType,ParameterValue=gp2 ParameterKey=hostedZoneId,ParameterValue=Z31TTYNFTW06J1 ParameterKey=dnsName,ParameterValue=ec2.csye6225-fall2017-patilm.me ParameterKey=recordSetType,ParameterValue=A ParameterKey=recordSetTTL,ParameterValue=60 ParameterKey=rdsParamUsername,ParameterValue=csye6225master ParameterKey=rdsParamPassword,ParameterValue=csye6225password 





