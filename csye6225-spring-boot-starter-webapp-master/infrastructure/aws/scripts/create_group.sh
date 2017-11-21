#!/bin/bash 

#Mihir Patil,     001220443, patil.m@husky.neu.edu
#Vivek Shetye,    001237626, shetye.v@husky.neu.edu
#Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
#Atul Takekar,    001220479, takekar.a@husky.neu.edu
         
groupName="csye6225-fall2017-webapp"
echo Please enter security group description:
read groupDescription
aws ec2 create-security-group --group-name $groupName --description $groupDescription
aws ec2 authorize-security-group-ingress --group-name $groupName --protocol tcp --port 22 --cidr 76.119.140.124/24
aws ec2 authorize-security-group-ingress --group-name $groupName --protocol tcp --port 80 --cidr 76.119.140.124/24
aws ec2 authorize-security-group-ingress --group-name $groupName --protocol tcp --port 443 --cidr 76.119.140.124/24


  
