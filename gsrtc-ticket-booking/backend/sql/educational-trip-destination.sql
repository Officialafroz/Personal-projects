DROP TABLE IF EXISTS Educational_Trip_Destination;

CREATE TABLE educational_trip_destination (
    destination_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_booking_id INT NOT NULL,                  -- FK to Educational_Trip_Request
    destination_name VARCHAR(150) NOT NULL,        -- Name of the stop
    sequence_no INT NOT NULL CHECK (sequence_no >= 1), -- Order of visit
    FOREIGN KEY (trip_booking_id) REFERENCES Educational_Trip_booking(trip_booking_id) 
        ON DELETE CASCADE
);
