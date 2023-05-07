#!/bin/bash

export JAVA_HOME="/home/mawy/.jdks/corretto-11.0.17"

version=1.0.0
host=media-pi.m3ns1.com
./build-service.sh

if [ $? -eq 0 ]; then

  echo Service wird nun auf das Raspi $host kopiert
  scp pom.xml pi@$host:/home/pi/server/words/pom.xml
  scp "target/languageteacher-1.0-SNAPSHOT.jar" pi@$host:/home/pi/server/words/app.jar

  read -p 'Service direkt neustarten? (yes/no) [no]: ' restart
  if [ "${restart,,}" = "yes" ] || [ "${restart,,}" = "y" ]; then
    ssh pi@$host '
cd /home/pi/server/words || exit 1
./rebuild.sh
cd ../traders-cockpit/ || exit 1
docker-compose up -d
exit
'
  fi
else
  echo Fehler beim Bauen des Services
fi
