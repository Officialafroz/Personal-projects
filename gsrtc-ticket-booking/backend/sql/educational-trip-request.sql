DROP TABLE IF EXISTS Educational_Trip_Request;

CREATE TABLE Educational_Trip_Request (
    trip_request_id INT PRIMARY KEY AUTO_INCREMENT,
    institute_id INT NOT NULL,                             -- FK to Institute_Registry
    depot_id INT NOT NULL,
    trip_name VARCHAR(20) NOT NULL,
    source_location VARCHAR(150) NOT NULL,                -- Starting point
    destination_location VARCHAR(150) NOT NULL,
    trip_days INT NOT NULL CHECK (trip_days >= 1),        -- Total trip days
    requested_buses INT NOT NULL, -- Number of buses
    total_persons INT NOT NULL,   -- Number of students
    bus_class VARCHAR(20) NOT NULL, -- Preferred bus class
    budget DECIMAL(8,2) DEFAULT 0.00,          -- Estimated total budget
    hotel_required BOOLEAN DEFAULT FALSE,                 -- Hotel stay needed
    trip_start_datetime DATETIME NOT NULL,                -- Start date/time
    emergency_contact VARCHAR(10) NOT NULL,   -- Emergency number
    status ENUM('PENDING', 'UNDER_PROCESS', 'APPROVED','REJECTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (institute_id) REFERENCES institute_registry(institute_id),
    FOREIGN KEY(depot_id) REFERENCES bus_depot(depot_id)
);