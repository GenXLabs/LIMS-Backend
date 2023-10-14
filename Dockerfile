FROM maven:3.8.3-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean install
WORKDIR /app/target
CMD ["java", "-jar", "lims-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082
