package com.example.demo.repository;

import com.example.demo.model.Prefecture;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PrefectureRepositoryImpl implements PrefectureRepository {

    @Override
    public Connection conn(String db_url, String db_user, String db_password) throws SQLException {
        return DriverManager.getConnection(db_url, db_user, db_password);
    }

    @Override
    public Prefecture findById(Connection connection, int prefectureNo) throws SQLException {
        String selectSQL = "SELECT DEFAULT_LATITUDE, DEFAULT_LONGITUDE, PREFECTURE_NAME FROM PREFECTURES WHERE NUMBER = ?";
        Prefecture prefecture = null;
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)){
            ps.setInt(1, prefectureNo);
            try(ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    float defaultLatitude = resultSet.getFloat("DEFAULT_LATITUDE");
                    float defaultLongitude = resultSet.getFloat("DEFAULT_LONGITUDE");
                    String prefectureName = resultSet.getString("PREFECTURE_NAME");
                    prefecture = new Prefecture(prefectureNo, prefectureName, defaultLatitude, defaultLongitude);
                }
            }
        }
        return prefecture;
    }
}
