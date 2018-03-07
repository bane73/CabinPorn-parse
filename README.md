# CabinPorn-parse

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
