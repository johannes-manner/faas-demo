## Devloping Lambda function with Eclipse

Installing AWS Toolkit for Eclipse.
Eclipse / Help / Install New Software / Work with: https://aws.amazon.com/eclipse and press Enter.

Select AWS Toolkit for Eclipse Core in the AWS Core Management Tools section plus other components you want to use.

Further Information: https://docs.aws.amazon.com/toolkit-for-eclipse/v1/user-guide/setup-install.html

## Setting up your AWS credentials in eclipse

To be able to use the Eclipse AWS Plugin, you have to configure your AWS Credentials. Further information: https://docs.aws.amazon.com/toolkit-for-eclipse/v1/user-guide/setup-credentials.html

## Get all items cloud Function - Doing all steps manually

Eclipse / File / New / Other / AWS / AWS Lambda Project

Use **Custom** as Input Type. Dependent on your use case, you can also use a pre-integrated type such as S3 Event or SNS Event and many more.

Implement your logic and deploy your function to a selected region. Therefore go to your handler class:

Right click -> AWS Lambda -> Upload Function to AWS Lambda:
 - Check the selected handler class
 - Select a region of your choice
 - Create a new Lambda function
 - Click **Next**
 - Create IAM Role and name it
 - Create S3 Bucket (storage location for your source code) and name it
 - Specify memory, e.g. 512
 - Timeout: 15
 - Click **Finish**

Test your function via the test utility in your lambda console on AWS.

## POST function - Store items in DynamoDb

A sample **post.json** is included in the project structure


Back to the [README](README.md).
