#!/usr/bin/env sh

###############################################################################
# SpringBoot App startup script
# Created by bmorel on 29/07/2020
###############################################################################

# Define correct LANG
export LANG=C

#$1 is the appName (ex: springboot_starter_sb2)

ConfDir="/var/opt/oss/data/config/"
WebAppDir="/var/opt/oss/data/config/"


# Define PID path & filename
PID_FILE=${ConfDir}$1.pid
PROCESS=`ps aux | egrep -i $1.jar | grep -v grep | awk '{ print $2; }'`



echo "///////////////////////////////////////////////////////////"
echo "          Stopping webapp $1"
echo "///////////////////////////////////////////////////////////"


if [ -z "$1" ]
then
    echo "ERROR a webapp name is needed"
    echo "./shutdown.sh <webb_app_name>"
    exit 1
fi

# If not logged in as 'temip' the script will exit
if [[ x${USER} != x"temip" ]]
then
    echo "You're logged in as ${USER}. $1 must be launch with temip user."
    exit 1
fi

echo "Stopping this webApp ..."
if [[ $PROCESS == +([0-9]) ]];
then
    kill -9 $PROCESS
    echo "$1 process is terminated"
else
    echo "WARNING $1 process is not running"
    exit 0
fi

echo "checking the stop of this webApp ..."
sleep 10
running_PROCESS=`ps aux | egrep -i $1.jar | grep -v grep | awk '{ print $2; }'`

i=1
while [[ $i -le 50 ]];
do

if [[ $running_PROCESS == +([0-9]) ]];
then
    echo "ERROR $1 process is still running with PID $running_PROCESS"
else
    echo "$1 process is not running"

    echo "removing the PID ..."
    if [ -f ${PID_FILE} ];
    then
        echo "PID file is deleted"
        rm ${PID_FILE} && exit 0
    else
        echo "WARNING There is probably a problem, the PID file doen't exist"
        exit 1
    fi

fi
sleep 5
i=$i+1
done






