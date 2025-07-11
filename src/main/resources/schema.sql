DROP TABLE IF EXISTS bulky_garbage_facilities;
DROP TABLE IF EXISTS garbage_type;
DROP TABLE IF EXISTS prefecture;

CREATE TABLE prefecture (
    number INT PRIMARY KEY,
    prefecture_name VARCHAR(50) NOT NULL,
    default_latitude DOUBLE PRECISION NOT NULL,
    default_longitude DOUBLE PRECISION NOT NULL
);

CREATE TABLE garbage_type (
    type_id INT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE bulky_garbage_facilities (
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    prefecture_no INT NOT NULL,
    facility_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    map_url VARCHAR(255) NOT NULL,
    garbage_type_id INT NOT NULL,
    PRIMARY KEY (latitude, longitude),
    FOREIGN KEY (prefecture_no) REFERENCES prefecture(number),
    FOREIGN KEY (garbage_type_id) REFERENCES garbage_type(type_id)
);
