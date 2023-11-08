FROM maven:3.8-openjdk-17-slim


RUN mkdir -p /home/app
WORKDIR /home/app


ADD pom.xml /home/app
ADD src /home/app/src


RUN mvn clean package
CMD ["java", "-jar", "/home/app/target/test-0.0.1-SNAPSHOT.jar"]