#!/bin/bash

echo "+++++++++ Start SSH."
service ssh start

echo "+++++++++ Format Hadoop."
/hadoop-3.3.6/bin/hdfs namenode -format

echo "+++++++++ Start DFS."
/hadoop-3.3.6/sbin/start-dfs.sh

sleep infinity