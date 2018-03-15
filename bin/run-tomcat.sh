#!/bin/sh

# /**
#  * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
#  *
#  * Author: ThinkGem@163.com
#  */

cd ../

# MAVEN_OPTS=$MAVEN_OPTS -Xms256m -Xmx1024m -XX:PermSize=128m -XX:MaxPermSize=512m
MAVEN_OPTS=$MAVEN_OPTS -Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

exec mvn clean spring-boot:run -U
