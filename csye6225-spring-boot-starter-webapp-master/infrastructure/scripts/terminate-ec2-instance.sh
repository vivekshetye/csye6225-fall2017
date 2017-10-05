

#!/bin/bash          
echo Please enter instance ID:
read INSTANCE_ID

aws ec2 modify-instance-attribute --instance-id $INSTANCE_ID --no-disable-api-termination

aws ec2 terminate-instances --instance-ids $INSTANCE_ID

aws ec2 wait instance-terminated --instance-ids $INSTANCE_ID

sh ./delete_group.sh  
