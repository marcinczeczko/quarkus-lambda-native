# Quarkus Lambda native talking to DynamodDB

Simple TODO application written in Quarkus:
- API endpoints driven by AWS Lambdas
- TODO items storage - AWS DynamoDB

Enjoy !

# Prerequisites
- Graalvm = 19.3.1
- [Serverless framework >= 1.56.1](https://serverless.com/framework/docs/getting-started/)
- AWS account

# Build Jvm & Native
`mvn clean package & mvn package -Dnative-lambda=true`

It will take some time....

# Run it
1. Deploy to AWS
`sls deploy -v`

2. Find out your API endoints
`sls info`

2. Add Todo item
- Using Native lambda
`curl -XPOST -H 'Content-Type: application/json' -d '' https://<apiid>.execute-api.<region>.amazonaws.com/dev/addNative`

- Using JVM lambda
`curl -XPOST -H 'Content-Type: application/json' -d '' https://<apiid>.execute-api.<region>.amazonaws.com/dev/addjvm`

5. Get all TODOs
- Using native lambda
`curl https://<apiid>.execute-api.<region>.amazonaws.com/dev/fetchNative`

- Using JVM lambda
`curl https://<apiid>.execute-api.<region>.amazonaws.com/dev/fetchJvm`

6. Don't forget to cleanup your AWS account if not using
`sls remove`