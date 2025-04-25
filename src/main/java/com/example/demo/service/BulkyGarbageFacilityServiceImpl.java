package com.example.demo.service;

import com.example.demo.model.BulkyGarbageFacility;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class BulkyGarbageFacilityServiceImpl implements BulkyGarbageFacilityService {

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String db_user;
    @Value("${spring.datasource.password}")
    private String db_password;

    @Autowired
    private BulkyGarbageFacilityRepository bulkyGarbageFacilityRepository;

    @Override
    public List<BulkyGarbageFacility> fetchBulkyGarbageFacility(int prefectureNo) throws SQLException {
        Connection conn = bulkyGarbageFacilityRepository.conn(db_url, db_user, db_password);
        return bulkyGarbageFacilityRepository.findByPrefectureNo(conn, prefectureNo);
    }
}
