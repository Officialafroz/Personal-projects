DROP TABLE IF EXISTS Educational_Trip_Booking;

CREATE TABLE Educational_Trip_Booking (
    trip_booking_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_request_id INT NOT NULL,                          -- Link to request
    pnr VARCHAR(12) UNIQUE NOT NULL,                      -- Unique trip booking PNR
    allocated_buses INT NOT NULL, -- Assigned buses
    total_fare DECIMAL(10,2) NOT NULL,                    -- Final fare
    booking_status ENUM('CONFIRMED','CANCELLED','COMPLETED') DEFAULT 'CONFIRMED',
    booked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (trip_request_id) REFERENCES Educational_Trip_Request(trip_request_id)
);
