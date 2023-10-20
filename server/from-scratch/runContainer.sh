#!/bin/bash

docker run --rm -it \
-p 9870:9870 \
--name hadoop-pseudo-distributed \
hadoop-pseudo-distributed