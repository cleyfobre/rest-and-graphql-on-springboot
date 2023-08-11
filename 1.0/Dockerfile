FROM adoptopenjdk/openjdk11
CMD ["./gradlew", "build", "-xtest"]
ARG JAR_FILE_PATH=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "app.jar"]
# docker build -t demo-web --build-arg SPRING_PROFILES_ACTIVE=dev .
# docker run -d --name dw -p 8080:8080 demo-web
