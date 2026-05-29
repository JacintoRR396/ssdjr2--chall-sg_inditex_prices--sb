FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd -ms /bin/bash spring
USER spring

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]