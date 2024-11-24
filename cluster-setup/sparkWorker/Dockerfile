FROM openjdk:13-alpine

MAINTAINER Florian Thom <s0558101@htw-berlin.de>

ENV IP_OF_HOST=NOT-GIVEN_EXAMPLE_10.0.0.10 IP_OF_MASTER=NOT-GIVEN_EXAMPLE_10.0.0.10 

RUN apk --no-cache add bash && \
		# spark script fails because something was missing, so we have to update coreutils
	apk --update add coreutils && \
		# spark script uses ps, but available ps (in image) is not the correct one, so we have to install it
	apk --no-cache add procps && \
	cd /home/ && \
	wget http://archive.apache.org/dist/spark/spark-2.4.2/spark-2.4.2-bin-hadoop2.7.tgz && \
	tar xvf spark-2.4.2-bin-hadoop2.7.tgz && \
	rm spark-2.4.2-bin-hadoop2.7.tgz && \
	cd spark-2.4.2-bin-hadoop2.7 && \
	mv /home/spark-2.4.2-bin-hadoop2.7/conf/spark-env.sh.template /home/spark-2.4.2-bin-hadoop2.7/conf/spark-env.sh

EXPOSE 8081

CMD ["bash","-c","echo SPARK_LOCAL_IP=$IP_OF_HOST>>/home/spark-2.4.2-bin-hadoop2.7/conf/spark-env.sh && /home/spark-2.4.2-bin-hadoop2.7/sbin/start-slave.sh $IP_OF_MASTER:7077 && bash"]



# worker is accessable via host- or container-ip

# ssh -L 127.0.0.1:8080:10.0.0.10:8080 desy-vm-on-node-1
# docker build -t sparkworker .
# docker run -d -t --restart=always -p 8081:8081 --network=host -e "IP_OF_HOST=192.168.2.197" -e "IP_OF_MASTER=192.168.2.160" flooth/spark-worker
# docker run -d -t --restart=always -p 8081:8081 --network=host -e "IP_OF_HOST=10.0.0.11" -e "IP_OF_MASTER=10.0.0.10" flooth/spark-worker
# docker exec -it ...

