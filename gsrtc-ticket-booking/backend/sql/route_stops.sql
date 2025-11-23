CREATE TABLE route_stop (
    stop_id INT PRIMARY KEY AUTO_INCREMENT,
    stop_name VARCHAR(100) NOT NULL,
    stop_order INT NOT NULL,
    distance_from_start DECIMAL(6,2) NOT NULL,
    fare DECIMAL(8,2) NOT NULL,
    route_id INT NOT NULL,
    FOREIGN KEY (route_id) REFERENCES bus_route(route_id) ON DELETE CASCADE
);

drop table route_stop;