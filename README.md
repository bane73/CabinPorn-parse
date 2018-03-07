# CabinPorn-parse

# SETUP
1. create an ops folder where we will run from (vs develop from)
   ```bash
   mkdir ~/ops
   ```
1. copy current service-script to ops folder and link to it from init.d
   ```bash
   cp ~/workspace/ops-support/cabinporn-parse.sh ~/ops
   sudo ln -s ~/ops/cabinporn-parse.sh /etc/init.d
   ```
1. copy current JAR to ops folder
   ```bash
   cp ~/workspace/target/cabinporn-parse.jar ~/ops

## OPS
* start the daemon
  ```bash
  sudo /etc/init.d/cabinporn-parse.sh start
  ```
* monitor the logs
  ```bash
  tail -f ops/cabinporn-parse.log
  ```
* get pid
  ```bash
  ps -ef | grep cabin
  ```
* kill the process
  ```bash
  sudo kill <pid>
  ```
