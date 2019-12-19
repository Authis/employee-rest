FROM alpine/git
WORKDIR /usr/app
RUN git clone https://github.com/Authis/employee-rest.git
FROM maven:3.5-jdk-8-alpine
WORKDIR /usr/app
COPY . .
RUN mvn install
FROM openjdk:8-jre-alpine
WORKDIR /usr/app
COPY --from=1 /usr/app/target/emp-reg-rest-1.0-SNAPSHOT.jar /usr/app
CMD ["java -jar target\emp-reg-rest-1.0-SNAPSHOT.jar"]
