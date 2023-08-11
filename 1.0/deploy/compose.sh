#!/bin/bash
echo ============ Compose the instance ===========

#did already on prior step in jenkins item
#mv /home/ubuntu/restart.sh /home/ubuntu/yangcheonroadapi/restart.sh

if command -v java >/dev/null 2>&1 ; then
    echo "jdk already installed"
else
    echo "install jdk"
    sudo apt update
    sudo apt install openjdk-17-jdk -y
fi

if command -v npm &> /dev/null; then
    echo "npm already installed"
else
    echo "install npm and pm2"
    sudo apt update
    sudo apt install npm -y
    sudo npm install --global pm2
    pm2 install pm2-logrotate
    pm2 set pm2-logrotate:dateFormat YYYY-MM-DD
    pm2 set pm2-logrotate:retain 5
    pm2 set pm2-logrotate:workerInterval 43200
fi
