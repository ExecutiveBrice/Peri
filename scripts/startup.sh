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
echo "          Starting webapp $1"
echo "///////////////////////////////////////////////////////////"



if [ -z "$1" ]
then
    echo "ERROR a webapp name is needed"
    echo "./startup.sh <webb_app_name>"
    exit 1
fi


# If not logged in as 'temip' the script will exit
if [[ x${USER} != x"temip" ]]
then
    echo "ERROR You're logged in as ${USER}. $1 must be launch with temip user."
    exit 1
fi

echo "Checking if this webApp is already running..."
if [[ $PROCESS == +([0-9]) ]];
then
    echo "WARNING $1 seems to be already running due to an existing process"
    exit 1
fi

if [ -f ${PID_FILE} ];
then
    rm ${PID_FILE}
    echo "WARNING $1 was not stopped correctly, PID file is deleted now"
fi

echo "Starting up java process in background for $1 ..."
WEBBAPP_ENV="dev"
WEBBAPP_CONFFILE="file:///var/opt/oss/data/config/$1/application.yml"
java -jar $OSS_JARDIR/$1.jar --spring.profiles.active=${WEBBAPP_ENV} --spring.config.location=${WEBBAPP_CONFFILE} $@ >> ${OSS_LOGDIR}/$1.out 2>&1 &




echo "Checking if the application running ..."
sleep 10
if [ -f ${PID_FILE} ];
then
    echo "PID file is created at $PID_FILE"
else
    echo "ERROR PID doesn't exist"
fi


PID=`cat ${PID_FILE}`
running_PROCESS=`ps aux | egrep -i $PID | grep -v grep | awk '{ print $2; }'`
i=1
while [[ $i -le 50 ]];
do

if [[ $running_PROCESS == +([0-9]) ]];
then
    echo "$1 process is running with PID $running_PROCESS"
    exit 0
else
    echo "WARNING process is not yet running"
fi
sleep 5
i=$i+1
done





