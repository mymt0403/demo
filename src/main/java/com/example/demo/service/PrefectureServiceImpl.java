package com.example.demo.service;

import com.example.demo.model.Prefecture;
import com.example.demo.repository.PrefectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrefectureServiceImpl implements PrefectureService {

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String db_user;
    @Value("${spring.datasource.password}")
    private String db_password;
    @Value("${default.tokyo-lat}")
    private Float tokyoLatitude;
    @Value("${default.tokyo-long}")
    private Float tokyoLongitude;

    @Autowired
    private PrefectureRepository prefectureRepository;

    @Override
    public List<Float> fetchCenterPosition(int prefectureNo) throws SQLException {
        Connection conn = prefectureRepository.conn(db_url, db_user, db_password);
        Prefecture prefecture = prefectureRepository.findById(conn, prefectureNo);
        List<Float> centerPosition = new ArrayList<>();
        Float defaultLatitude = prefecture.getDefaultLatitude();
        Float defaultLongitude = prefecture.getDefaultLongitude();
//        if (defaultLatitude != null || defaultLongitude != null) {
//            centerPosition.add(prefecture.getDefaultLatitude());
//            centerPosition.add(prefecture.getDefaultLongitude());
//        } else {
//            // 値が取得できなかった場合は東京の座標を返す
//            centerPosition.add(tokyoLatitude);
//            centerPosition.add(tokyoLongitude);
//        }
        centerPosition.add(defaultLatitude);
        centerPosition.add(defaultLongitude);
        return centerPosition;
    }
}
