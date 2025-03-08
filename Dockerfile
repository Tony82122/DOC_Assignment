FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

# Copy maven to the image
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src


RUN chmod +x ./mvnw

RUN ./mvnw package -DskipTests


FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]