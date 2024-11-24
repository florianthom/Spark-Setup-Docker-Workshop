FROM openjdk:13-alpine

MAINTAINER Florian Thom <s0558101@htw-berlin.de>

ENV IP_OF_HOST=NOT-GIVEN_EXAMPLE_10.0.0.10

RUN apk --no-cache add bash && \
	apk --update add coreutils && \
	apk --no-cache add procps && \
	#apk add --no-cache ca-certificates && \
	#apk add --no-cache curl jq && \
	cd /home/ && \
	wget http://archive.apache.org/dist/spark/spark-2.4.2/spark-2.4.2-bin-hadoop2.7.tgz && \
	tar xvf spark-2.4.2-bin-hadoop2.7.tgz && \
	rm spark-2.4.2-bin-hadoop2.7.tgz && \
	cd spark-2.4.2-bin-hadoop2.7 && \
	mv /home/spark-2.4.2-bin-hadoop2.7/conf/spark-env.sh.template /home/spark-2.4.2-bin-hadoop2.7/conf/spark-env.sh

EXPOSE 8080 7077 6066
CMD bash -c 'echo -e "SPARK_MASTER_HOST=$IP_OF_HOST\nSPARK_LOCAL_IP=$IP_OF_HOST">>/home/spark-2.4.2-bin-hadoop2.7/conf/spark-env.sh';'/home/spark-2.4.2-bin-hadoop2.7/sbin/start-master.sh';'bash'


# docker build -t sparkmaster .
# docker tag sparkmaster flooth/spark-master
# docker push flooth/spark-master
# docker run -d -t --restart=always -v /home/flo/Desktop/github/spark-Master-Worker-Docker/sparkMaster/apps/:/apps -p 8080:8080 -p 7077:7077 -p 6066:6066 --network=host -e "IP_OF_HOST=192.168.2.160" flooth/spark-master
#docker run -d -t --restart=always -v /home/vagrant/shareWithHost/sparkApps/:/apps -p 8080:8080 -p 7077:7077 -p 6066:6066 --network=host -e "IP_OF_HOST=10.0.0.10" flooth/spark-master



#ssh -L 127.0.0.1:8080:127.0.0.1:8080 troester
# inside vagrant folder: ssh -L 127.0.0.1:8080:127.0.0.1:8080 $(vagrant ssh-config | awk 'NR>1 {print " -o "$1"="$2}') localhost
