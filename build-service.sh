#!/bin/bash

export JAVA_HOME="/home/mawy/.jdks/corretto-11.0.17"

mvn clean verify

exit $?