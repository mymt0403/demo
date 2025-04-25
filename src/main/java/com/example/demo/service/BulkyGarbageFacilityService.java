package com.example.demo.service;

import com.example.demo.model.BulkyGarbageFacility;

import java.sql.SQLException;
import java.util.List;

public interface BulkyGarbageFacilityService {

    List<BulkyGarbageFacility> fetchBulkyGarbageFacility(int prefectureNo) throws SQLException;
}
