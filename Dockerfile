FROM openjdk:11
LABEL maintainer="Alexandre Franca"
ADD build/libs/library-events-producer-0.0.1-SNAPSHOT.jar library-events-producer.jar
ENTRYPOINT ["java", "-jar", "library-events-producer.jar"]