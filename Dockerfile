FROM openjdk:17.0.1-jdk-slim
ADD target/mentormate-server-0.0.1-SNAPSHOT.jar mentormate-server-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","mentormate-server-0.0.1-SNAPSHOT.jar"]