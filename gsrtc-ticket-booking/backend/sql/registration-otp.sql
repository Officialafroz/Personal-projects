CREATE TABLE registration_otp (
id INT AUTO_INCREMENT PRIMARY KEY,
otp INT NOT NULL UNIQUE,
email varchar(30) NOT NULL
);

USE gsrtc_clone;
DROP TABLE registration_otp;