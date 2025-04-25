package com.example.demo.repository;

import com.example.demo.model.Prefecture;

import java.sql.Connection;
import java.sql.SQLException;

public interface PrefectureRepository {

    Connection conn(String db_url, String db_user, String db_password) throws SQLException;

    Prefecture findById(Connection connection, int prefectureNo) throws SQLException;
}
