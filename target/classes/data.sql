CREATE DATABASE IF NOT EXISTS Pressure;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    gender VARCHAR(1),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pressures (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      upperPressure INT,
      lowerPressure INT,
      pulse INT,
      startedMeassure TIME,
      userId BIGINT,
      FOREIGN KEY (userId) REFERENCES user(id)
);

