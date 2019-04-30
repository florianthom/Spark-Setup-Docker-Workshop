FROM openjdk:13-alpine

MAINTAINER Florian Thom <s0558101@htw-berlin.de>

RUN apk --no-cache add bash && \
	apk --update add coreutils && \
	apk --no-cache add procps && \
	#apk add --no-cache ca-certificates && \
	#apk add --no-cache curl jq && \
	cd /home/ && \
	wget http://ftp.fau.de/apache/spark/spark-2.4.2/spark-2.4.2-bin-hadoop2.7.tgz && \
	tar xvf spark-2.4.2-bin-hadoop2.7.tgz && \
	rm spark-2.4.2-bin-hadoop2.7.tgz && \
	cd spark-2.4.2-bin-hadoop2.7

EXPOSE 8080 7077 6066
CMD bash -C '/home/spark-2.4.2-bin-hadoop2.7/sbin/start-master.sh';'bash'


# docker build -t sparkmaster .
# docker run -d -t --restart=always -p 8080:8080 -p 7077:7077 -p 6066:6066 sparkmaster
# docker exec -it flamboyant_colden bash
