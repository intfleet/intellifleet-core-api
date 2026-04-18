FROM eclipse-temurin:21-jdk
# Create log directory
RUN mkdir -p /opt/intellifleet/apps/logs/intellifleet-core-api
EXPOSE 8086
COPY target/intellifleet-core-api.jar intellifleet-core-api-app.jar
ENTRYPOINT ["java","-jar","/intellifleet-core-api-app.jar"]