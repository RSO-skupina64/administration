FROM maven:3.8.6-openjdk-18 AS build
COPY ./ /app
WORKDIR /app
RUN mvn --show-version --update-snapshots --batch-mode clean package

FROM amazoncorretto:18
RUN mkdir /app
WORKDIR /app
COPY --from=build ./app/target/administration.jar /app
EXPOSE 8080 9090
CMD ["java", "-jar", "administration.jar"]
