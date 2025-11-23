	CREATE TABLE Booking (
		booking_id INT PRIMARY KEY AUTO_INCREMENT,
        trip_id INT NOT NULL,
        user_id INT NOT NULL,
		journey_date DATE NOT NULL,
		pnr VARCHAR(10) UNIQUE NULL,        -- Unique alphanumeric
		total_fare DECIMAL(10,2) NOT NULL,
		booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		status VARCHAR(20) DEFAULT 'CONFIRMED',
		FOREIGN KEY (user_id) REFERENCES user(uid) ON DELETE CASCADE,
		FOREIGN KEY (trip_id) REFERENCES trip(trip_id) ON DELETE CASCADE
	);
    
drop table booking;

-- Add the trip_id column as NOT NULL with foreign key
-- ALTER TABLE Booking
-- ADD COLUMN trip_id INT NOT NULL,
-- ADD CONSTRAINT booking_ibfk_3 FOREIGN KEY (trip_id) REFERENCES Bus_Trip(trip_id);

-- SELECT * FROM booking;
