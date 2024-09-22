FROM amazoncorretto:23-alpine AS builder

RUN apk add --no-cache maven

WORKDIR /app

COPY . .

RUN mvn clean package

FROM amazoncorretto:23-alpine as final

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

COPY .env /app.env

ENTRYPOINT ["java", "-jar", "app.jar"]