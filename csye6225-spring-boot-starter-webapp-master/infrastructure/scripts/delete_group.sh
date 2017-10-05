#!/bin/bash          

GROUP_NAME="csye6225-fall2017-webapp"
aws ec2 delete-security-group --group-name $GROUP_NAME


