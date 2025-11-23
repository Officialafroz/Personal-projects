CREATE TABLE passenger (
    passenger_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    seat_no VARCHAR(10) NOT NULL,
    boarding_point VARCHAR(100) NOT NULL,
    destination_point VARCHAR(100) NOT NULL,
    individual_fare DECIMAL(10,2) NULL,
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id) ON DELETE CASCADE
);

drop table passenger;