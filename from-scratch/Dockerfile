FROM ubuntu
COPY scripts/init.sh scripts/init.sh
RUN chmod 744 scripts/init.sh \
&& scripts/init.sh
COPY config/ hadoop-3.3.6/etc/hadoop/
COPY scripts/startup.sh scripts/startup.sh
RUN chmod 744 scripts/startup.sh
CMD [ "sh", "-c", "/scripts/startup.sh"]

