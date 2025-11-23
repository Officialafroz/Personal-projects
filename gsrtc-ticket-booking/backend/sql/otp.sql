CREATE TABLE otp_details (
	otp_id INT PRIMARY KEY AUTO_INCREMENT,
    otp VARCHAR(6) NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES user(uid)
);

-- ALTER TABLE otp
-- ADD COLUMN expires_at VARCHAR(50) AFTER otp_id,
-- ADD COLUMN lastsent_at VARCHAR(50) AFTER expires_at;

-- ALTER TABLE otp
-- ADD COLUMN otp INT AFTER otp_id;

 ALTER TABLE otp_details
DROP CONSTRAINT otp_details_ibfk_1;

drop table otp_details;


drop table otp_details; 