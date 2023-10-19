#!/bin/bash

echo "+++++++++ Install Prerequisite."
apt-get update && apt-get install -y \
    openjdk-8-jdk \
    ssh

echo "+++++++++ Install Hadoop Package."
wget https://dlcdn.apache.org/hadoop/common/hadoop-3.3.6/hadoop-3.3.6.tar.gz
tar xzf hadoop-3.3.6.tar.gz
echo "PATH=\$PATH:/hadoop-3.3.6/bin
PATH=\$PATH:/hadoop-3.3.6/sbin" \
>> /root/.bashrc && source /root/.bashrc
echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-arm64
export HDFS_NAMENODE_USER=root
export HDFS_DATANODE_USER=root
export HDFS_SECONDARYNAMENODE_USER=root
" >> /hadoop-3.3.6/etc/hadoop/hadoop-env.sh
hadoop version

echo "+++++++++ Configure SSH."
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys
