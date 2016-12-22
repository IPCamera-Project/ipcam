#!/usr/bin/env bash

mvn clean install -DskipTests=true

mkdir plugins

cp hikvision/target/*.jar plugins/
cp vivotek/target/*.jar plugins/
cd -

