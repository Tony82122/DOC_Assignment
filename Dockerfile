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

RUN echo 'parse_git_branch() {' >> /root/.bashrc && \
    echo '  git branch 2> /dev/null | sed -e "/^[^*]/d" -e "s/* \(.*\)/(\1)/"' >> /root/.bashrc && \
    echo '}' >> /root/.bashrc && \
    echo 'PS1="\[\033[32m\]vscode \[\033[34m\]→ \[\033[36m\]/workspaces/DOC_Assignment \[\033[31m\]$(parse_git_branch)\[\033[00m\] "' >> /root/.bashrc
ENTRYPOINT ["java","-jar","/app.jar"]
