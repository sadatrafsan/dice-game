FROM openjdk:11
EXPOSE 8080
ADD target/dice-game-1.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]