package com.example.dao;

import com.example.demo.BulkyGarbageFacility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.config.DatabaseConfig.*;

public class BulkyGarbageFacilityDAO {

    public static Connection conn() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void insertFacility(Connection connection, int latitude, int longitude, String prefecture, String facilityName) throws SQLException {
        String insertSQL = "INSERT INTO BULKY_GARBAGE_FACILITIES (LATITUDE, LONGITUDE, PREFECTURE, FACILITY_NAME) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, latitude);
            preparedStatement.setInt(2, longitude);
            preparedStatement.setString(3, prefecture);
            preparedStatement.setString(4, facilityName);
            preparedStatement.executeUpdate();
        }
    }

    public static List<BulkyGarbageFacility> fetchFacilities(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM BULKY_GARBAGE_FACILITIES";
        List<BulkyGarbageFacility> facilities = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(selectSQL);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                float latitude = resultSet.getFloat("LATITUDE");
                float longitude = resultSet.getFloat("LONGITUDE");
                String prefecture = resultSet.getString("PREFECTURE");
                String facilityName = resultSet.getString("FACILITY_NAME");
                BulkyGarbageFacility facility = new BulkyGarbageFacility(latitude, longitude, prefecture, facilityName);
                facilities.add(facility);
            }
        }
        return facilities;
    }
}
