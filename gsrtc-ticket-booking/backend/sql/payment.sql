DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(10,2) NOT NULL,
    payment_method ENUM('CASH', 'CARD', 'UPI', 'NETBANKING') NOT NULL,
    status ENUM('PENDING', 'PAID', 'FAILED') DEFAULT 'PAID',
    transaction_ref VARCHAR(50) UNIQUE,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    booking_id INT,                               -- FK for regular tickets
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id) ON DELETE CASCADE
);

drop table payment;

-- ALTER TABLE payment
-- ADD CONSTRAINT payment_idbk_2
-- FOREIGN KEY (edu_booking_id)
-- REFERENCES Educational_Trip_Booking(booking_id)
-- ON DELETE CASCADE
-- ON UPDATE CASCADE;

-- select * from payment
