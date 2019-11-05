# Prerequisites
- Graalvm >= 19.2.1
- [Serverless framework >= 1.56.1](https://serverless.com/framework/docs/getting-started/)

# Configure serverless framework
`npm install`

# Build
`mvn clean package -Dnative=true -Dnative-image.docker-build=true`

# Invoke local lambda as Java runtime
* Run
`sls invoke local --docker -f helloJvm --path event.json`

* Result
```
Serverless: Packaging service...
Serverless: Building Docker image...
2019-10-31 14:48:19,911 INFO  [io.quarkus] (main) quarkus-lambda-native 2.0-SNAPSHOT (running on Quarkus 0.27.0) started in 3.914s. 
2019-10-31 14:48:19,920 INFO  [io.quarkus] (main) Profile prod activated. 
2019-10-31 14:48:19,921 INFO  [io.quarkus] (main) Installed features: [amazon-lambda, cdi]
START RequestId: 2f0ec79e-a40e-47a5-ad11-e7be3ea84386 Version: $LATEST
2019-10-31 14:48:20,068 INFO  [io.git.mar.HelloLambda] (main) [2f0ec79e-a40e-47a5-ad11-e7be3ea84386] Processed data
END RequestId: 2f0ec79e-a40e-47a5-ad11-e7be3ea84386
REPORT RequestId: 2f0ec79e-a40e-47a5-ad11-e7be3ea84386  Duration: 172.45 ms     Billed Duration: 200 ms Memory Size: 1536 MB    Max Memory Used: 7 MB   

{"statusCode":200,"headers":null,"body":"{\"greeting\":\"Hello Hello World.\",\"context\":\"lambdainternal.api.LambdaContext@4141d797\"}"}
```

# Invoke local lambda as Native runtime
`sls invoke local --docker -f helloNative --path event.json`


* Result
```
Serverless: Packaging service...
Serverless: Building Docker image...
2019-11-04 08:16:20,650 INFO  [io.quarkus] (main) quarkus-lambda-native 2.0-SNAPSHOT (running on Quarkus 0.27.0) started in 0.033s. 
2019-11-04 08:16:20,652 INFO  [io.quarkus] (main) Profile prod activated. 
2019-11-04 08:16:20,652 INFO  [io.quarkus] (main) Installed features: [amazon-lambda, cdi]
START RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72 Version: $LATEST
2019-11-04 08:16:20,658 INFO  [io.git.mar.HelloLambda] (Lambda Thread) [52fdfc07-2182-154f-163f-5f0f9a621d72] Processed data
END RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72
REPORT RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72  Init Duration: 98.96 ms Duration: 8.64 ms       Billed Duration: 100 ms Memory Size: 1536 MB    Max Memory Used: 21 MB  

{"statusCode":200,"headers":null,"body":"{\"greeting\":\"Hello Hello World.\",\"context\":\"io.quarkus.amazon.lambda.runtime.AmazonLambdaContext@7f996da1e7a0\"}"}
```

# Run on AWS (API Gateway + Lambda)
* [Serverless framework](https://serverless.com/framework/docs/providers/aws/guide/quick-start/) is required

1. Deploy to AWS
`sls deploy -v`

2. Invoke a JVM function directly
`sls invoke -f helloJvm -l --path event.json`

3. Invoke a Native function directly
`sls invoke -f helloNative -l --path event.json`

4. Invoke a JVM function via API gateway
`curl -s 'https://<apiid>.execute-api.eu-central-1.amazonaws.com/dev/jvm?firstname=jon&lastname=doe'`

5. Invoke a JVM function via API gateway
`curl -s 'https://<apiid>.execute-api.eu-central-1.amazonaws.com/dev/native?firstname=jon&lastname=doe'`
