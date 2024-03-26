# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación Spring Boot al contenedor
COPY target/CupTap_RESTAPI-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecuta dentro del contenedor
EXPOSE 1243

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]