#!/usr/bin/env bash

SERVER_NAME=$1
JAR_NAME=SERVER_NAME.jar

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi
PIDS=`ps -ef | grep java | grep "$JAR_NAME" |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "INFO: The $SERVER_NAME does not started!"
    exit
fi

echo -e "Stopping the $SERVER_NAME ...\c"
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
done
COUNT=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done
echo "OK!"