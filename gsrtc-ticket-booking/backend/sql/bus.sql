CREATE TABLE bus(
	bus_id INT PRIMARY KEY AUTO_INCREMENT,
    bus_number VARCHAR(12) UNIQUE NOT NULL,
    bus_type VARCHAR(50) NOT NULL,
    depot_id INT,
    layout_id INT,
    FOREIGN KEY(depot_id) REFERENCES bus_depot(depot_id),
    FOREIGN KEY(layout_id) REFERENCES bus_layout(layout_id)
);

use gsrtc_clone;

ALTER TABLE bus
DROP CONSTRAINT bus_ibfk_2;




drop table bus;

SELECT * FROM bus;