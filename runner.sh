#!/usr/bin/env bash

mvn clean install -DskipTests=true

mkdir IPCAM-VIDEO-STREAMING-API/plugins

cp hikvision/target/*.jar IPCAM-VIDEO-STREAMING-API/plugins/
cp vivotek/target/*.jar IPCAM-VIDEO-STREAMING-API/plugins/
cd -

