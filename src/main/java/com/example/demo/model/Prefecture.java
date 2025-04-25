package com.example.demo.model;

import lombok.Value;

import java.io.Serializable;

@Value
public class Prefecture implements Serializable {
    int number; // 都道府県コード
    String prefectureName; // 県名
    float defaultLatitude; // 中央座標の緯度
    float defaultLongitude; // 中央座標の経度
}
