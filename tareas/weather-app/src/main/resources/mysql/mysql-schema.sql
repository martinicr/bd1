CREATE DATABASE weather_service;

USE weather_service;

CREATE TABLE forecast(
    forecast_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    country_name VARCHAR(100) NOT NULL,
    city_name VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    forecast_date DATETIME NOT NULL,
    temperature FLOAT NOT NULL
);