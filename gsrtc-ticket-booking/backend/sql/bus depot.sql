CREATE TABLE bus_depot(
	depot_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL
);

drop table bus_depot;

use gsrtc_clone;