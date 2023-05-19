# Stage 0: Create jar with JDK
FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins

COPY . .
RUN mvn package -DskipTests

# Stage 1: Run jar with JRE
FROM eclipse-temurin:17-jre-focal AS deploy

COPY --from=build /app/target/tp-0.0.1-SNAPSHOT.jar /app/main.jar
CMD ["java", "-jar", "/app/main.jar"]