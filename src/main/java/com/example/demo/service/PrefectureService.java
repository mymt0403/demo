package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

public interface PrefectureService {

    List<Float> fetchCenterPosition(int prefectureNo) throws SQLException;
}
