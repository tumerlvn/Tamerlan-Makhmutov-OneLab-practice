DROP TABLE customers IF EXISTS;
DROP TABLE flights IF EXISTS;
DROP TABLE tickets IF EXISTS;


CREATE TABLE IF NOT EXISTS customers(id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(150), email VARCHAR(150), hashedPassword VARCHAR(150), passportNumber VARCHAR(150));
CREATE TABLE IF NOT EXISTS flights(id INT PRIMARY KEY AUTO_INCREMENT, aircraftCode VARCHAR(150), departureCity VARCHAR(150), arrivalCity VARCHAR(150), departureTime DATE, arrivalTime DATE);
CREATE TABLE IF NOT EXISTS tickets(id INT PRIMARY KEY AUTO_INCREMENT, customer_id INT, flight_id INT);