FROM amazoncorretto:17
WORKDIR /app
COPY applications/app-service/build/libs/guardians.jar guardians.jar
ENV JAVA_OPTS="-Xmx512m -Xms256m"

CMD ["sh", "-c", "java $JAVA_OPTS -jar guardians.jar"]