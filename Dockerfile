FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/reto

COPY .mvn ./.mvn
COPY mvnw .
COPY pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/reto/target/reto-0.0.1-SNAPSHOT.jar .

ENV PORT 8003
EXPOSE $PORT

CMD ["java", "-jar", "reto-0.0.1-SNAPSHOT.jar"]