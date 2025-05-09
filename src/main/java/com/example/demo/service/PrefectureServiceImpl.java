package com.example.demo.service;

import com.example.demo.model.Position;
import com.example.demo.repository.PrefectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PrefectureServiceImpl implements PrefectureService {
    @Value("${default.tokyo-lat}")
    private Float tokyoLatitude;
    @Value("${default.tokyo-long}")
    private Float tokyoLongitude;

    @Autowired
    PrefectureRepository prefectureRepository;

    @Override
    public Position getCenterPosition(int prefectureNo) {
        return prefectureRepository.findById(prefectureNo)
                .map(prefecture -> new Position(prefecture.getDefaultLatitude(), prefecture.getDefaultLongitude()))
                // 値が取得できなかった場合は東京の座標を返す
                .orElse(new Position(tokyoLatitude, tokyoLongitude));
    }
}
