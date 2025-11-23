CREATE TABLE trip (
    trip_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_code VARCHAR(20) UNIQUE NOT NULL, -- e.g., AHMSRT_MORNING
    date DATE NOT NULL,
    departure_time DATETIME NOT NULL,
    destination_time DATETIME NOT NULL,
    status VARCHAR(15) DEFAULT 'ACTIVE',  -- ACTIVE/INACTIVE,
    bus_id INT NOT NULL,
    route_id INT NOT NULL,
    depot_id INT NOT NULL,
    FOREIGN KEY (bus_id) REFERENCES bus(bus_id),
    FOREIGN KEY (route_id) REFERENCES bus_route(route_id),
    FOREIGN KEY (depot_id) REFERENCES bus_depot(depot_id)
);

drop table trip;

use gsrtc_clone;

drop table trip;

ALTER TABLE trip
ADD CONSTRAINT bus_id_const
FOREIGN KEY (bus_id)
REFERENCES bus (bus_id);

-- ALTER TABLE trip

ALTER TABLE trip 
MODIFY COLUMN departure_time TIME NOT NULL,
MODIFY COLUMN destination_time TIME NOT NULL;

ALTER table trip 
drop constraint trip_ibfk_2;

-- ALTER TABLE bus_trip
-- ADD COLUMN trip_date DATE NOT NULL AFTER route_id;