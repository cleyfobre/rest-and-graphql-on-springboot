#!/bin/bash
echo ============ Restart Yangcheon Road API On PM2 ===========

if [ -f /home/ubuntu/backend/yangcheonroadapi-0.0.1-SNAPSHOT.jar ]; then
        echo "file exist"
        pm2 list
        pm2 stop yangcheon-road-api
        rm /home/ubuntu/backend/repo/yangcheonroadapi-backup.jar
        cp /home/ubuntu/backend/yangcheonroadapi-0.0.1-SNAPSHOT.jar /home/ubuntu/repo/yangcheonroadapi-backup.jar
        rm /home/ubuntu/backend/yangcheonroadapi-0.0.1-SNAPSHOT.jar
        cp /home/ubuntu/backend/repo/yangcheonroadapi-0.0.1-SNAPSHOT.jar /home/ubuntu/yangcheonroadapi
        rm /home/ubuntu/backend/repo/yangcheonroadapi-0.0.1-SNAPSHOT.jar
        pm2 list
        pm2 reload /home/ubuntu/backend/app.json
        pm2 list
else
        echo "file not exit"
        pm2 list
        cp /home/ubuntu/backend/repo/yangcheonroadapi-0.0.1-SNAPSHOT.jar /home/ubuntu/backend
        rm /home/ubuntu/backend/repo/yangcheonroadapi-0.0.1-SNAPSHOT.jar
        pm2 start /home/ubuntu/backend/app.json
        pm2 list
fi