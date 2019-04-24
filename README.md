# Prerequisites
- Graalvm >= rc15
- [Serverless framework >= 1.41.1](https://serverless.com/framework/docs/getting-started/)

# Build
`mvn clean package -DskipTests=true -Dnative=true -Dnative-image.docker-build=true`

# Invoke local lambda as Java runtime
* Run
`sls invoke local -f hello --path event.json`

* Result
```
Serverless: Packaging service...
Serverless: Building Docker image...
START RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72 Version: $LATEST

2019-04-24 12:56:22,672 INFO  [io.git.mar.HelloLambda] (Lambda Thread) Processed data
Test lambda logger
END RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72

REPORT RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72  Init Duration: 1073.42 ms       Duration: 76.57 ms      Billed Duration: 100 ms Memory Size: 1536 MB    Max Memory Used: 87 MB  

{"statusCode":200,"headers":null,"body":"{\"greeting\":\"Hello Hello World.\",\"context\":\"io.quarkus.amazon.lambda.runtime.AmazonLambdaContext@14964b0f\"}"}
```

# Invoke local lambda as Native runtime
`sls invoke local -f hello --path event.json --type native`


* Result
```
Serverless: Packaging service...
Serverless: Building Docker image...
START RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72 Version: $LATEST

2019-04-24 12:55:54,340 INFO  [io.git.mar.HelloLambda] (Lambda Thread) Processed data
Test lambda logger2019-04-24 12:55:54,340 INFO  [io.quarkus] (main) Quarkus 999-SNAPSHOT started in 0.045s. Listening on: http://0.0.0.0:8080
2019-04-24 12:55:54,343 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy, resteasy-jsonb, smallrye-rest-client]

END RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72
REPORT RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72  Init Duration: 96.86 ms Duration: 8.46 ms       Billed Duration: 100 ms Memory Size: 1536 MB    Max Memory Used: 25 MB  

{"statusCode":200,"headers":null,"body":"{\"greeting\":\"Hello Hello World.\",\"context\":\"io.quarkus.amazon.lambda.runtime.AmazonLambdaContext@7ff564a18800\"}"}
```

# Run on AWS (API Gateway + Lambda)
* [Serverless framework](https://serverless.com/framework/docs/providers/aws/guide/quick-start/) is required

1. Deploy to AWS
 - as a java function`sls deploy --type java -v`
 - or, as a native function `sls deploy --type native -v`

2. Invoke a function directly
`sls invoke -f hello -l --path event.json`

3. Invoke a function via API gateway
`curl -s 'https://<apiid>.execute-api.eu-central-1.amazonaws.com/dev/user?firstname=jon&lastname=doe'`

