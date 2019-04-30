FROM openjdk:13-alpine

MAINTAINER Florian Thom <s0558101@htw-berlin.de>

RUN apk --no-cache add bash && \
		# spark script fails because something was missing, so we have to update coreutils
	apk --update add coreutils && \
		# spark script uses ps, but available ps (in image) is not the correct one, so we have to install it
	apk --no-cache add procps && \
	cd /home/ && \
	wget http://ftp.fau.de/apache/spark/spark-2.4.2/spark-2.4.2-bin-hadoop2.7.tgz && \
	tar xvf spark-2.4.2-bin-hadoop2.7.tgz && \
	rm spark-2.4.2-bin-hadoop2.7.tgz && \
	cd spark-2.4.2-bin-hadoop2.7

EXPOSE 8081

CMD ["bash","-c","/home/spark-2.4.2-bin-hadoop2.7/sbin/start-slave.sh 192.168.2.160:7077 && bash"]



# worker is accessable via 192.168.2.160:7077 or ipOfDockerContainer:7077 (e.g. 172.17.0.2:7077)

# docker build -t sparkworker .
# docker run -d -t --restart=always -p 8081:8081 sparkworker
# docker exec -it flamboyant_colden bash
