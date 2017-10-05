#!/bin/bash    

#Mihir Patil,     001220443, patil.m@husky.neu.edu
#Vivek Shetye,    001237626, shetye.v@husky.neu.edu
#Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
#Atul Takekar,    001220479, takekar.a@husky.neu.edu
      

GROUP_NAME="csye6225-fall2017-webapp"
aws ec2 delete-security-group --group-name $GROUP_NAME


