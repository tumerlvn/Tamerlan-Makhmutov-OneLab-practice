INSERT INTO customers(username, email, hashedPassword, passportNumber) VALUES('tamer', 'tamer@mail.ru', 'superSecret', '123');
INSERT INTO customers(username, email, hashedPassword, passportNumber) VALUES('alish', 'alish@mail.ru', 'superSecret', '321');
INSERT INTO customers(username, email, hashedPassword, passportNumber) VALUES('user', 'user@mail.ru', 'superSecret', '555');

INSERT INTO flights(aircraftCode, departureCity, arrivalCity, departureTime) VALUES('207', 'Astana', 'Almaty', '29-07-2020');
INSERT INTO flights(aircraftCode, departureCity, arrivalCity, departureTime) VALUES('207', 'Almaty', 'Astana', '30-07-2020');
INSERT INTO flights(aircraftCode, departureCity, arrivalCity, departureTime) VALUES('503', 'Almaty', 'Berlin', '30-07-2020');

INSERT INTO tickets(customer_id, flight_id, departureDateTime) VALUES(1, 1, '29-07-2020');
INSERT INTO tickets(customer_id, flight_id, departureDateTime) VALUES(1, 3, '30-07-2020');

