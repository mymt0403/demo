DROP TABLE IF EXISTS PREFECTURE;
DROP TABLE IF EXISTS GARBAGE_TYPE;
DROP TABLE IF EXISTS BULKY_GARBAGE_FACILITIES;

CREATE TABLE PREFECTURE (
    NUMBER INT NOT NULL,
    PREFECTURE_NAME VARCHAR(50) NOT NULL,
    DEFAULT_LATITUDE FLOAT NOT NULL,
    DEFAULT_LONGITUDE FLOAT NOT NULL,
    PRIMARY KEY (NUMBER)
);

CREATE TABLE GARBAGE_TYPE (
    TYPE_ID INT NOT NULL,
    TYPE_NAME VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (TYPE_ID)
);

CREATE TABLE BULKY_GARBAGE_FACILITIES (
    LATITUDE FLOAT NOT NULL,
    LONGITUDE FLOAT NOT NULL,
    PREFECTURE_NO INT NOT NULL,
    FACILITY_NAME VARCHAR(255) NOT NULL,
    ADDRESS VARCHAR(255) NOT NULL,
    MAP_URL VARCHAR(255) NOT NULL,
    GARBAGE_TYPE_ID INT NOT NULL,
    PRIMARY KEY (LATITUDE, LONGITUDE),
    FOREIGN KEY (PREFECTURE_NO) REFERENCES PREFECTURE(NUMBER),
    FOREIGN KEY (GARBAGE_TYPE_ID) REFERENCES GARBAGE_TYPE(TYPE_ID)
);
