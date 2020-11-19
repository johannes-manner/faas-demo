# Cloud Function Demo

## Setup

### AWS Account

Get an AWS account to start your cloud experience on Amazon Web Services: https://aws.amazon.com/

### Development with Eclipse

If you are interested you can develop Java functions with Eclipse, but that's for now not the focus of this show case tutorial.
Further infos are [here](eclipse.md).


### Setting up your AWS credentials

Installing the AWS CLI on your machine and configure it.


## Szenario

This is the overview of our scenario, including an API Gateway with a GET and POST method, two AWS Lambda Functions and a DynamoDb with a single table to store the orders.

![alt text](scenario.png)

#### POST function - Store items in DynamoDb

A sample **post.json** is included in the project structure.

## Automating your Infrastructure Creation via CloudFormation

Because setting up every component (DynamoDb, API Gateway, IAM, Lambdas) is tedious, we use **Infrastructure as Code** offering on AWS to automate this and ease our application setup.

To setup your infrastructure and defining your application as presented in the scenario, execute the following bash command.
There is a temporary file created, since the S3 bucket name must be unique within the AWS cloud.
Therefore a random string generation is used and the bucket name is stored in this config file:

```bash
>bash deploy.sh
```

To tear down your infrastructure and delete all artifacts on your cloud provider's ecosystem, execute:

```bash
>bash undeploy.sh
```

**REMARKS**

Suffix **--capabilities CAPABILITY_NAMED_IAM** is needed since we are creating a IAM Role for our lambda functions.

**Useful Tools**

 ```bash
 >cfn-flip cloudFormation.json cloudFormation.yaml
 ```
For deploying your CloudFormation stack via the CLI, the .yaml configuration is used. Since writing YAML is error-prone and tedious, the AWS Labs on GitHub presented a tool to convert JSON to valid YAML, which was used during development.

 Source: https://github.com/awslabs/aws-cfn-template-flip

## Accessing your API

When your cloud formation deployment ends, you get a JSON like the following - some parts are left blank :)
 ```
 {
     "Stacks": [
         {
             "StackId": "...",
             "StackName": "serverless-stack-....",
             "Description": "Creates a dynamoDb instance",
             "Parameters": [
                 {
                     "ParameterKey": "ItemApiStage",
                     "ParameterValue": "dev"
                 },
                 {
                     "ParameterKey": "DSGBucket",
                     "ParameterValue": "serverless-bucket-...."
                 }
             ],
             "CreationTime": "2020-11-19...",
             "RollbackConfiguration": {},
             "StackStatus": "CREATE_COMPLETE",
             "DisableRollback": false,
             "NotificationARNs": [],
             "Capabilities": [
                 "CAPABILITY_NAMED_IAM"
             ],
             "Outputs": [
                 {
                     "OutputKey": "ItemApiUrl",
                     "OutputValue": "https://API-STAGE.execute-api.eu-central-1.amazonaws.com/dev",
                     "Description": "The url of the item api"
                 }
             ],
             "Tags": [],
             "EnableTerminationProtection": false,
             "DriftInformation": {
                 "StackDriftStatus": "NOT_CHECKED"
             }
         }
     ]
 }
 ```

So the result is a public API at endpoint: https://API-STAGE.execute-api.eu-central-1.amazonaws.com/dev.

You can now CURL the GET endpoint via the base API path and the **items** subpath:
```
$ curl https://API-STAGE.execute-api.eu-central-1.amazonaws.com/dev/items
```
The same path is used for POSTing data:
```
$ curl -X POST -H "Content-Type: application/json" -d @./post.json https://API-Stage.execute-api.eu-central-1.amazonaws.com/dev/items
```
