#Stage 1: Build the Jar file
FROM openjdk:11-jdk-slim as builder

LABEL MAINTAINER="asatinihal2009@nineleaps.com"

WORKDIR /app

#copy wrapper and pom
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

#add executable permission to maven wrapper and download dependency(or use cached) 
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline -B

COPY src src

#Build Jar
RUN ./mvnw package -DskipTests

#create  folder and decouple files from jar
RUN mkdir -p target/dependency

WORKDIR /app/target/dependency

RUN jar -xf ../*.jar
#Adding user with least privilidges
RUN groupadd -r leastprivgroup && useradd -r -g leastprivgroup demouser

USER demouser

# Production Stage for Spring boot application image
FROM openjdk:11.0.8-jre-slim as production
ARG DEPENDENCY=/app/target/dependency

# Copy dependency application file from build stage artifact
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

# Run the Spring boot application
ENTRYPOINT ["java", "-cp", "app:app/lib/*","com.medicare.MedicareApplication"]