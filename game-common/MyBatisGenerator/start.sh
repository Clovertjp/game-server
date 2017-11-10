#!/bin/bash

RUN_DIR=$(dirname $0)

java -jar "$RUN_DIR/lib/mybatis-generator-core-1.3.5.jar" -configfile "$RUN_DIR/conf/generate.xml" -overwrite
