CREATE TABLE bus_route (
    route_id INT PRIMARY KEY AUTO_INCREMENT,
    route_name VARCHAR(100) NOT NULL,
    start_point VARCHAR(100) NOT NULL,
    end_point VARCHAR(100) NOT NULL,
    depot_id int NOT NULL,
    FOREIGN KEY (depot_id) REFERENCES bus_depot(depot_id)
);

drop table bus_route;

use gsrtc_clone;