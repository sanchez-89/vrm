#!/bin/bash
cd /home/vuelogix/vrm
/usr/bin/java -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=10090 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -jar /home/vuelogix/vrm/VueRecordingManager-0.0.1-SNAPSHOT.jar --spring.config.location=file:/home/vuelogix/vrm/application.properties >> /home/vuelogix/vrm/logging.txt
