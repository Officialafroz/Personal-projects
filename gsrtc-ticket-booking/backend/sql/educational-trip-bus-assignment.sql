DROP TABLE IF EXISTS Educational_Trip_Bus_Assignment;
CREATE TABLE educational_trip_bus_assignment (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_booking_id INT NOT NULL,         -- FK to Educational_Trip_Booking
    bus_id INT NOT NULL,                  -- FK to Bus
    driver_id INT DEFAULT NULL,           -- FK to Staff
    conductor_id INT,                     -- FK to Staff
    status ENUM('ASSIGNED', 'COMPLETED', 'CANCELLED') DEFAULT 'ASSIGNED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (trip_booking_id) REFERENCES Educational_Trip_Booking(trip_booking_id) ON DELETE CASCADE,
    FOREIGN KEY (bus_id) REFERENCES Bus(bus_id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES Staff(staff_id) ON DELETE SET NULL,
    FOREIGN KEY (conductor_id) REFERENCES Staff(staff_id) ON DELETE SET NULL
);
