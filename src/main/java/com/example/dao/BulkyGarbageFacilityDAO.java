package com.example.dao;

import com.example.demo.BulkyGarbageFacility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BulkyGarbageFacilityDAO {
    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            insertFacility(connection, 35, 139, "Tokyo", "Facility A");
            insertFacility(connection, 34, 135, "Osaka", "Facility B");
            List<BulkyGarbageFacility> facilities = fetchFacilities(connection);
            facilities.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertFacility(Connection connection, int latitude, int longitude, String prefecture, String facilityName) throws SQLException {
        String insertSQL = "INSERT INTO BULKY_GARBAGE_FACILITIES (LATITUDE, LONGITUDE, PREFECTURE, FACILITY_NAME) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, latitude);
            preparedStatement.setInt(2, longitude);
            preparedStatement.setString(3, prefecture);
            preparedStatement.setString(4, facilityName);
            preparedStatement.executeUpdate();
        }
    }

    private static List<BulkyGarbageFacility> fetchFacilities(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM BULKY_GARBAGE_FACILITIES";
        List<BulkyGarbageFacility> facilities = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            while (resultSet.next()) {
                int latitude = resultSet.getInt("LATITUDE");
                int longitude = resultSet.getInt("LONGITUDE");
                String prefecture = resultSet.getString("PREFECTURE");
                String facilityName = resultSet.getString("FACILITY_NAME");
                BulkyGarbageFacility facility = new BulkyGarbageFacility(latitude, longitude, prefecture, facilityName);
                facilities.add(facility);
            }
        }
        return facilities;
    }
}
