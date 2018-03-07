#! /bin/sh

### BEGIN INIT INFO
# Provides: cabinporn-parse
# Required-Start: $remote_fs $syslog
# Required-Stop: $remote_fs $syslog
# Default-Start: 2 3 4 5
# Default-Stop: 0 1 6
# Short-Description: CabinPorn-parse
# Description: This file starts and stops CabinPorn-parse daemon
# 
### END INIT INFO

### SETUP & OPS
# // create an ops folder where we will run from (vs develop from)
# mkdir ~/ops
#
# // copy current service-script to ops folder and link to it from init.d
# cp ~/workspace/ops-support/cabinporn-parse.sh ~/ops
# sudo ln -s ~/ops/cabinporn-parse.sh /etc/init.d
#
# // copy current JAR to ops folder
# cp ~/workspace/target/cabinporn-parse.jar ~/ops
#
# // start the daemon
# sudo /etc/init.d/cabinporn-parse.sh start
#
# // monitor the logs
# tail -f ops/cabinporn-parse.log
#
# // get pid
# ps -ef | grep cabin
#
# // kill the process
# sudo kill <pid>

case "$1" in
  start)
		cd /home/cabox/workspace
		nohup java -jar target/cabinporn-parse.jar 'loop=true' 'sleepTimeMs=60000' 'pageDepthToSearch=5' 'searchWords=Brandon, Gresham, Norm, Dawn, Smith, Montana, Fortine, Lincoln, 1985' >> ~/ops/cabinporn-parse.log &
		;;
  stop)
		;;
  restart)
		;;
  *)
		echo "Oops!" >&2
		exit3
		;;
esac

