FROM openjdk:17
COPY target/streak-app-0.0.1-SNAPSHOT.jar /app/streak-app-0.0.1-SNAPSHOT.jar
COPY config.properties /app/config.properties
COPY ./database/streak-app-database-file.mv.db /app/database/streak-app-database-storage.mv.db
ENV DB_PATH=/app/database/streak-app-database-storage
ENV CONFIG_PROPERTIES_PATH=/app/config.properties
ENTRYPOINT ["java","-jar","/app/streak-app-0.0.1-SNAPSHOT.jar"]