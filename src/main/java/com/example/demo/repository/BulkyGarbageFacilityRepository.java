package com.example.demo.repository;

import com.example.demo.model.BulkyGarbageFacility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BulkyGarbageFacilityRepository {

    Connection conn(String db_url, String db_user, String db_password) throws SQLException;

    List<BulkyGarbageFacility> findByPrefectureNo(Connection connection, int prefectureNo) throws SQLException;
}
