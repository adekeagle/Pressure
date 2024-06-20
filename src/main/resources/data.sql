CREATE TABLE user (
    id BIGINT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    gender VARCHAR(1),
    email VARCHAR(255)
);

CREATE TABLE pressure (
      id BIGINT PRIMARY KEY,
      upperPressure INT,
      lowerPressure INT,
      pulse INT,
      startedMeassure TIME,
      userId BIGINT,
      FOREIGN KEY (userId) REFERENCES user(id)
  );

