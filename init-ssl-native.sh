#!/bin/bash

docker run --rm --entrypoint cat quay.io/quarkus/ubi-quarkus-native-image:19.2.1 /opt/graalvm/jre/lib/security/cacerts > docker-ssl/cacerts
docker run --rm --entrypoint cat quay.io/quarkus/ubi-quarkus-native-image:19.2.1 /opt/graalvm/jre/lib/amd64/libsunec.so > docker-ssl/libsunec.so


