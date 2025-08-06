-- Create the database
CREATE DATABASE IF NOT EXISTS p26__tinyfurdb;
USE p26__tinyfurdb;

-- Create Role table
CREATE TABLE IF NOT EXISTS Role (
    roleid INT AUTO_INCREMENT PRIMARY KEY,
    rolename VARCHAR(20) NOT NULL UNIQUE
);

-- Create State table
CREATE TABLE IF NOT EXISTS State (
    stid INT AUTO_INCREMENT PRIMARY KEY,
    stname VARCHAR(100) NOT NULL UNIQUE
);

-- Create City table
CREATE TABLE IF NOT EXISTS City (
    cityid INT AUTO_INCREMENT PRIMARY KEY,
    cityname VARCHAR(50) NOT NULL UNIQUE,
    stid INT NOT NULL,
    FOREIGN KEY (stid) REFERENCES State(stid)
);

-- Create User table with password field
CREATE TABLE IF NOT EXISTS User (
    userid INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    contact_no VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    aadhar_no BIGINT NOT NULL,
    cityid INT NOT NULL,
    roleid INT NOT NULL,
    password VARCHAR(255) NOT NULL,
    security_question VARCHAR(255),
    security_answer VARCHAR(255),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cityid) REFERENCES City(cityid),
    FOREIGN KEY (roleid) REFERENCES Role(roleid)
);

-- Create Breed table
CREATE TABLE IF NOT EXISTS Breed (
    bid INT AUTO_INCREMENT PRIMARY KEY,
    bname VARCHAR(100) NOT NULL
);

-- Create Pet table with vaccination status
CREATE TABLE IF NOT EXISTS Pet (
    p_id INT AUTO_INCREMENT PRIMARY KEY,
    p_name VARCHAR(100) NOT NULL,
    p_age INT NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    p_photo VARCHAR(225) NOT NULL,
    fid INT,
    mid INT,
    seller_id INT NOT NULL,
    is_vaccinated BOOLEAN NOT NULL DEFAULT FALSE,
    price FLOAT NOT NULL,
    description TEXT,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fid) REFERENCES Breed(bid),
    FOREIGN KEY (mid) REFERENCES Breed(bid),
    FOREIGN KEY (seller_id) REFERENCES User(userid)
);

-- Create BookPet table
CREATE TABLE IF NOT EXISTS BookPet (
    bk_id INT AUTO_INCREMENT PRIMARY KEY,
    p_id INT NOT NULL,
    Price FLOAT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    buyer_id INT NOT NULL,
    status ENUM('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (p_id) REFERENCES Pet(p_id),
    FOREIGN KEY (buyer_id) REFERENCES User(userid)
);

-- Create Bill table
CREATE TABLE IF NOT EXISTS Bill (
    blid INT AUTO_INCREMENT PRIMARY KEY,
    useracc BIGINT NOT NULL,
    Amt FLOAT NOT NULL,
    date DATE NOT NULL,
    bk_id INT NOT NULL,
    payment_method VARCHAR(50),
    transaction_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bk_id) REFERENCES BookPet(bk_id)
);

-- Insert initial data for roles
INSERT INTO Role (rolename) VALUES ('Admin'), ('User') ON DUPLICATE KEY UPDATE rolename=VALUES(rolename);

-- Insert sample states
INSERT INTO State (stname) VALUES 
('Maharashtra'), ('Karnataka'), ('Tamil Nadu'), ('Gujarat'), ('Rajasthan'), 
('Uttar Pradesh'), ('West Bengal'), ('Delhi'), ('Punjab'), ('Haryana')
ON DUPLICATE KEY UPDATE stname=VALUES(stname);

-- Insert sample cities for Maharashtra (stid=1)
INSERT INTO City (cityname, stid) VALUES 
('Mumbai', 1), ('Pune', 1), ('Nagpur', 1), ('Nashik', 1), ('Aurangabad', 1)
ON DUPLICATE KEY UPDATE cityname=VALUES(cityname);

-- Insert sample cities for Karnataka (stid=2)
INSERT INTO City (cityname, stid) VALUES 
('Bengaluru', 2), ('Mysuru', 2), ('Hubli', 2), ('Mangaluru', 2), ('Belagavi', 2)
ON DUPLICATE KEY UPDATE cityname=VALUES(cityname);

-- Insert sample cities for Tamil Nadu (stid=3)
INSERT INTO City (cityname, stid) VALUES 
('Chennai', 3), ('Coimbatore', 3), ('Madurai', 3), ('Tiruchirappalli', 3), ('Salem', 3)
ON DUPLICATE KEY UPDATE cityname=VALUES(cityname);

-- Insert sample cities for Gujarat (stid=4)
INSERT INTO City (cityname, stid) VALUES 
('Ahmedabad', 4), ('Surat', 4), ('Vadodara', 4), ('Rajkot', 4), ('Bhavnagar', 4)
ON DUPLICATE KEY UPDATE cityname=VALUES(cityname);

-- Insert sample cities for Delhi (stid=8)
INSERT INTO City (cityname, stid) VALUES 
('New Delhi', 8), ('North Delhi', 8), ('South Delhi', 8), ('East Delhi', 8), ('West Delhi', 8)
ON DUPLICATE KEY UPDATE cityname=VALUES(cityname);

-- Insert dog breeds
INSERT INTO Breed (bname) VALUES 
('Labrador Retriever'), ('Golden Retriever'), ('German Shepherd'), ('Bulldog'), 
('Poodle'), ('Beagle'), ('Rottweiler'), ('Yorkshire Terrier'), ('Dachshund'), 
('Siberian Husky'), ('Boxer'), ('Great Dane'), ('Pomeranian'), ('Boston Terrier'), 
('Shih Tzu'), ('Chihuahua'), ('Border Collie'), ('Cocker Spaniel'), ('Dalmatian'), 
('Maltese'), ('Basset Hound'), ('Newfoundland'), ('Saint Bernard'), ('Doberman Pinscher'),
('Indian Pariah Dog'), ('Rajapalayam'), ('Chippiparai'), ('Kanni'), ('Kombai'), ('Mudhol Hound')
ON DUPLICATE KEY UPDATE bname=VALUES(bname);

-- Insert admin user (password should be hashed in real implementation)
INSERT INTO User (username, email, contact_no, address, aadhar_no, cityid, roleid, password, first_name, last_name) VALUES 
('admin', 'admin@tinyfur.com', '9999999999', 'Admin Address', 123456789012, 1, 1, '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Admin', 'User')
ON DUPLICATE KEY UPDATE email=VALUES(email);

-- Insert sample user
INSERT INTO User (username, email, contact_no, address, aadhar_no, cityid, roleid, password, first_name, last_name) VALUES 
('testuser', 'user@tinyfur.com', '8888888888', 'User Address', 123456789013, 1, 2, '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Test', 'User')
ON DUPLICATE KEY UPDATE email=VALUES(email); 