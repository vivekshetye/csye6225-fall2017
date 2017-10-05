#!/bin/bash



#Mihir Patil,     001220443, patil.m@husky.neu.edu
#Vivek Shetye,    001237626, shetye.v@husky.neu.edu
#Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
#Atul Takekar,    001220479, takekar.a@husky.neu.edu


echo "Please enter Stack Name"
read stackName
echo "Please enter Amazon Machine Image Id"
read ImgId
echo "Please enter the instance type"
read InstType
echo "Please enter the volume size"
read volumeSize
echo "Please enter the volume type"
read volumeType
echo "Please enter the public key name"
read keyName
echo "Please enter the security group name"
read securityGroupName
echo "Please enter the hosted Zone Id"
read hostedZoneId
echo "Please enter the dns name"
read dnsName
echo "Please enter the RecordSet Type"
read recordSetType
echo "Please enter the RecordSeT Time to Live"
read recordSetTTL

aws cloudformation create-stack --stack-name $stackName --enable-termination-protection --template-body file:///home/patilmihir/Assignment_4/PART_2/Demo.json --parameters ParameterKey=ImgId,ParameterValue=$ImgId ParameterKey=InstType,ParameterValue=$InstType ParameterKey=volumeSize,ParameterValue=$volumeSize ParameterKey=volumeType,ParameterValue=$volumeType ParameterKey=keyName,ParameterValue=$keyName ParameterKey=securityGroupName,ParameterValue=$securityGroupName ParameterKey=hostedZoneId,ParameterValue=$hostedZoneId ParameterKey=dnsName,ParameterValue=$dnsName ParameterKey=recordSetType,ParameterValue=$recordSetType ParameterKey=recordSetTTL,ParameterValue=$recordSetTTL








