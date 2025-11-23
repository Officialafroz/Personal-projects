CREATE TABLE depot_admin(
	depot_admin_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(6) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
	gender VARCHAR(6) NOT NULL,	
    role varchar(12) NOT NULL,
    depot_id int NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (depot_id) REFERENCES bus_depot(depot_id)
);

-- ALTER TABLE depot_admin
-- MODIFY COLUMN role varchar(12) DEFAULT 'DEPOT_ADMIN';

-- ALTER TABLE depot_admin
-- ADD COLUMN password VARCHAR(72) NOT NULL AFTER email;

DROP TABLE depot_admin;
