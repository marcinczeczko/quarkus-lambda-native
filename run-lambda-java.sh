#!/bin/bash

cat event.json | docker run --rm -v "$PWD/target/function-java/amazon-lambda-dynamodb-1.0-SNAPSHOT":/var/task -i -p 5005:5005 -e DOCKER_LAMBDA_USE_STDIN=1 lambci/lambda:provided handler