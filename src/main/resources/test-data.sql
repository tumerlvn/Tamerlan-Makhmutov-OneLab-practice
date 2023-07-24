INSERT INTO customers(username, email, hashedPassword, passportNumber) VALUES('tamer', 'tamer@mail.ru', 'superSecret', '123');
INSERT INTO customers(username, email, hashedPassword, passportNumber) VALUES('alish', 'alish@mail.ru', 'superSecret', '321');
INSERT INTO customers(username, email, hashedPassword, passportNumber) VALUES('user', 'user@mail.ru', 'superSecret', '555');

INSERT INTO flights(aircraftCode, departureCity, arrivalCity, departureTime, arrivalTime) VALUES('207', 'Astana', 'Almaty', '2023-08-01', '2023-08-01');
INSERT INTO flights(aircraftCode, departureCity, arrivalCity, departureTime, arrivalTime) VALUES('207', 'Almaty', 'Astana', '2023-08-02', '2023-08-03');
INSERT INTO flights(aircraftCode, departureCity, arrivalCity, departureTime, arrivalTime) VALUES('503', 'Almaty', 'Berlin', '2023-08-02', '2023-08-03');

INSERT INTO tickets(customer_id, flight_id) VALUES(1, 1);
INSERT INTO tickets(customer_id, flight_id) VALUES(1, 3);

