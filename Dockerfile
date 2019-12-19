#FROM alpine/git
#WORKDIR /usr/app
#RUN git clone https://github.com/Authis/employee-rest.git
#FROM maven:3.5-jdk-8-alpine
#WORKDIR /usr/app
#COPY . .
#RUN mvn install


#FROM openjdk:8-jre-alpine
#COPY  ./target/emp-reg-rest-1.0-SNAPSHOT.jar ./
#RUN echo "$PWD"
#CMD ["java -jar emp-reg-rest-1.0-SNAPSHOT.jar"]



FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ./
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/emp-reg-rest-1.0-SNAPSHOT.jar"]



#FROM openjdk:8-jre-alpine
 
#COPY ./target/… ./
 
#CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default”, "/app.war"]
