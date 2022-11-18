FROM openjdk
ADD target/proyecto-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]


# mvn clean package
# docker build -t 6regorio/hola:hola .
# docker run -p 8080:8080 --env SPRING_PROFILES_ACTIVE=docker 6regorio/hola:hola