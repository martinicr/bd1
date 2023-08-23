Weather-App
===========

[![Weather-App Build Pipeline](https://github.com/martinicr/bd1/actions/workflows/weather-app-build.yml/badge.svg?branch=main)](https://github.com/martinicr/bd1/actions/workflows/weather-app-build.yml)


Aplicacion para obtener y publicar pronosticos el clima.

## Intrucciones de construcción:

```bash
# compilar, compilar recursos
mvn clean compile

# compilar, compilar recursos, compilar codigo de pruebas unitarias, compilar recursos de pruebas, 
# ejecutar pruebas unitarias
mvn clean test

# compilar, compilar recursos, compilar codigo de pruebas unitarias, compilar recursos de pruebas, 
# ejecutar pruebas unitarias, crear JAR SIN dependencias.
mvn clean package

# compilar, compilar recursos, compilar codigo de pruebas unitarias, compilar recuersos de pruebas, 
# ejecutar pruebas unitarias, crear JAR CON dependencias.
mvn clean package assembly:single

# compilar, compilar recursos, compilar codigo de pruebas unitarias, compilar recuersos de pruebas, ejecutar pruebas unitarias, 
# crear JAR CON dependencias, instalar JAR SIN dependencias en repositorio local. (.m2/repository)
mvn clean install 
```

## Instrucciones de ejecución:

```bash
# Ver opciones disponibles
java -jar target/forecast-app-<version>-jar-with-dependencies.jar

# Obtener pronóstico del clima por ciudad
java -jar target/forecast-app-<version>-jar-with-dependencies.jar by-city 'Alajuela'
java -jar target/forecast-app-<version>-jar-with-dependencies.jar bc 'Alajuela'

# Crear un nuevo pronóstico del clima en una ciudad
java -jar target/forecast-app-<version>-jar-with-dependencies.jar create-forecast 11 'United States' 'New York' '2023-08-19' 10001 23
java -jar target/forecast-app-<version>-jar-with-dependencies.jar cf 11 'United States' 'New York' '2023-08-19' 10001 23

# Actualizar un pronóstico del clima
java -jar target/forecast-app-<version>-jar-with-dependencies.jar update-forecast 11 'United States' 'New York' '2023-08-19' 10001 23
java -jar target/forecast-app-<version>-jar-with-dependencies.jar uf 11 'United States' 'New York' '2023-08-19' 10001 23

# Borrar un pronóstico del clima
java -jar target/forecast-app-<version>-jar-with-dependencies.jar rf 1 

```


