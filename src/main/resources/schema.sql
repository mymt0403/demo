CREATE TABLE IF NOT EXISTS BULKY_GARBAGE_FACILITIES (
    LATITUDE FLOAT NOT NULL,
    LONGITUDE FLOAT NOT NULL,
    PREFECTURE CHAR(255) NOT NULL,
    FACILITY_NAME CHAR(255) NOT NULL,
    PRIMARY KEY (LATITUDE, LONGITUDE)
);
