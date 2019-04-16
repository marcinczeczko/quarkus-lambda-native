# Build
`mvn clean package -DskipTests=true -Dnative=true -Dnative-image.docker-build=true`

# Run locally using lambci docker
* Run using Java
`./run-lambda-java.sh`

* Result
```

```

* Run using native image
`./run-lambda-native.sh`

* Result - missing reflection for inner classes
```

```