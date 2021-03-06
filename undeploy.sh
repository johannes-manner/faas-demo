#!/bin/bash

FILE="config"
if [ -f $FILE ]; then
   echo "File $FILE exists."
   UNIQUE_ID=$(head -n 1 $FILE)
else
   echo "File $FILE does not exist."
   exit
fi

REGION="eu-central-1"
STACK_NAME="serverless-stack-$UNIQUE_ID"
BUCKET_NAME="serverless-bucket-$UNIQUE_ID"

echo "Undeploying your cloud formation stack and also the S3 bucket, where your functions are located"
echo ""

echo "aws --region $REGION cloudformation delete-stack --stack-name $STACK_NAME"
aws --region $REGION cloudformation delete-stack --stack-name $STACK_NAME
aws --region $REGION cloudformation wait stack-delete-complete --stack-name $STACK_NAME

echo "Stack $STACK_NAME undeployed successfully"
echo ""

echo "aws --region $REGION s3 rb s3://$BUCKET_NAME --force"
aws --region $REGION s3 rb s3://$BUCKET_NAME --force

echo "Successfully deleted S3 bucket - $BUCKET_NAME"
echo ""

echo "Succesfully deleted all resources"

rm $FILE
echo "File $FILE removed"
