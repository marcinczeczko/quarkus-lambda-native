# Build
`mvn clean package -DskipTests=true -Dnative=true -Dnative-image.docker-build=true`

# Run locally using lambci docker
* Run using Java
`./run-lambda-java.sh`

* Result
```
START RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72 Version: $LATEST
END RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72
REPORT RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72  Init Duration: 4217.75 ms       Duration: 157.31 ms     Billed Duration: 200 ms Memory Size: 1536 MB    Max Memory Used: 78 MB  
{"statusCode":200,"headers":null,"body":"Hello Hello World."}
```

* Run using native image
`./run-lambda-native.sh`

* Result - missing reflection for inner classes
```
START RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72 Version: $LATEST
END RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72
REPORT RequestId: 52fdfc07-2182-154f-163f-5f0f9a621d72  Init Duration: 105.90 ms        Duration: 10.50 ms      Billed Duration: 100 ms Memory Size: 1536 MB    Max Memory Used: 24 MB  
{
  "errorType": "com.fasterxml.jackson.databind.exc.InvalidDefinitionException",
  "errorMessage": "Cannot construct instance of `com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent$ProxyRequestContext` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)\n at [Source: (sun.net.www.protocol.http.HttpURLConnection$HttpInputStream); line: 38, column: 5] (through reference chain: com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent[\"requestContext\"])"
}
```

Same error is thrown on AWS