package com.example.demo.service;

import com.example.demo.model.Prefecture;
import com.example.demo.repository.PrefectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrefectureServiceImpl implements PrefectureService {
    @Value("${default.tokyo-lat}")
    private Float tokyoLatitude;
    @Value("${default.tokyo-long}")
    private Float tokyoLongitude;

    @Autowired
    PrefectureRepository prefectureRepository;

    @Override
    public List<Float> fetchCenterPosition(int prefectureNo) {
        Optional<Prefecture> prefecture = prefectureRepository.findById(prefectureNo);
        List<Float> centerPosition = new ArrayList<>();
        prefecture.ifPresentOrElse(x ->{
            centerPosition.add(x.getDefaultLatitude());
            centerPosition.add(x.getDefaultLongitude());
        }, () -> {
            // 値が取得できなかった場合は東京の座標を返す
            centerPosition.add(tokyoLatitude);
            centerPosition.add(tokyoLongitude);
        });
        return centerPosition;
    }
}
