FROM maven:latest
LABEL authors="saeidt"

WORKDIR /app
COPY pom.xml /app
COPY . /app
RUN mvn package
CMD ["java", "-cp", "target/TemperatureConverterAssignement-1.0-SNAPSHOT.jar", "TemperatureConverter"]