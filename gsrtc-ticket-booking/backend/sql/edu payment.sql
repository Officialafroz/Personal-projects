DROP TABLE IF EXISTS edu_payment;

CREATE TABLE edu_payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    edu_booking_id INT,                               -- FK for regular tickets
    amount DECIMAL(10,2) NOT NULL,
    method ENUM('CASH', 'CARD', 'UPI', 'NETBANKING') NOT NULL,
    status ENUM('PENDING','PROCESSING', 'PAID', 'FAILED'),
    transaction_ref VARCHAR(50) UNIQUE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (edu_booking_id) REFERENCES educational_trip_booking(trip_booking_id) ON DELETE CASCADE
);
