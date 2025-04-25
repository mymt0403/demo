package com.example.demo.repository;

import com.example.demo.model.BulkyGarbageFacility;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BulkyGarbageFacilityRepositoryImpl implements BulkyGarbageFacilityRepository {

    @Override
    public Connection conn(String db_url, String db_user, String db_password) throws SQLException {
        return DriverManager.getConnection(db_url, db_user, db_password);
    }

    @Override
    public List<BulkyGarbageFacility> findByPrefectureNo(Connection connection, int prefectureNo) throws SQLException {
        String selectSQL = "SELECT LATITUDE, LONGITUDE, P.PREFECTURE_NAME, FACILITY_NAME FROM BULKY_GARBAGE_FACILITIES AS BGF INNER JOIN PREFECTURES AS P ON P.NUMBER = BGF.PREFECTURE_NO WHERE P.NUMBER = ?";
        List<BulkyGarbageFacility> facilities = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)){
            ps.setInt(1, prefectureNo);
            try(ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    float latitude = resultSet.getFloat("LATITUDE");
                    float longitude = resultSet.getFloat("LONGITUDE");
                    String prefecture = resultSet.getString("PREFECTURE_NAME");
                    String facilityName = resultSet.getString("FACILITY_NAME");
                    BulkyGarbageFacility facility = new BulkyGarbageFacility(latitude, longitude, prefecture, facilityName);
                    facilities.add(facility);
                }
            }
        }
        return facilities;
    }
}
