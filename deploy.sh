#!/bin/bash

FILE="config"

bash generateUniqueId.sh $FILE

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

echo "Deploy your demo application"
echo ""

echo "aws --region $REGION s3api create-bucket --bucket $BUCKET_NAME --create-bucket-configuration LocationConstraint=$REGION"
aws --region $REGION s3api create-bucket --bucket $BUCKET_NAME --create-bucket-configuration LocationConstraint=$REGION

echo ""
echo "Build the jar files of your lambdas"
mvn -f UseCase/AllItems package
mvn -f UseCase/StoreOrder package

echo ""
echo "Upload the jar files to your created S3 bucket"
echo "aws --region $REGION s3api put-object --bucket $BUCKET_NAME --key AllItems.jar --body UseCase/AllItems/target/get-1.0.jar"
aws --region $REGION s3api put-object --bucket $BUCKET_NAME --key AllItems.jar --body UseCase/AllItems/target/get-1.0.jar
echo "aws --region $REGION s3api put-object --bucket $BUCKET_NAME --key StoreOrder.jar --body UseCase/StoreOrder/target/post-1.0.jar"
aws --region $REGION s3api put-object --bucket $BUCKET_NAME --key StoreOrder.jar --body UseCase/StoreOrder/target/post-1.0.jar

echo ""
echo "aws --region $REGION cloudformation create-stack --stack-name $STACK_NAME --template-body file://cloudFormation.yaml --parameters ParameterKey=DSGBucket,ParameterValue=$BUCKET_NAME --capabilities CAPABILITY_NAMED_IAM"
aws --region $REGION cloudformation create-stack --stack-name $STACK_NAME --template-body file://cloudFormation.yaml --parameters ParameterKey=DSGBucket,ParameterValue=$BUCKET_NAME --capabilities CAPABILITY_NAMED_IAM

echo ""
echo "Get infos, especially the Api endpoint"
echo "aws --region $REGION cloudformation describe-stacks --stack-name $STACK_NAME"
aws --region $REGION cloudformation describe-stacks --stack-name $STACK_NAME

echo "aws cloudformation wait stack-create-complete --stack-name $STACK_NAME"
aws --region $REGION cloudformation wait stack-create-complete --stack-name $STACK_NAME
echo "aws --region $REGION cloudformation describe-stacks --stack-name $STACK_NAME"
aws --region $REGION cloudformation describe-stacks --stack-name $STACK_NAME
