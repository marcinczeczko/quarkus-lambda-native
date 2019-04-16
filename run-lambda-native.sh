#!/bin/bash

cat event.json | docker run --rm -v "$PWD/target/function-native/amazon-lambda-dynamodb-1.0-SNAPSHOT":/var/task -i -e DOCKER_LAMBDA_USE_STDIN=1 lambci/lambda:provided handler