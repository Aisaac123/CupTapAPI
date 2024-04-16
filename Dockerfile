
FROM maven:3.8.4-openjdk-11 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package

# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Copia el archivo JAR de la aplicación Spring Boot al contenedor
COPY --from=build /app/target/CupTap_RESTAPI-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecuta dentro del contenedor
EXPOSE 1243

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]