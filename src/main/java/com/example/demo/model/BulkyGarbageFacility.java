package com.example.demo.model;

import lombok.Data;

@Data
public class BulkyGarbageFacility {

    private float latitude; // 緯度
    private float longitude; // 経度
    private String prefecture; // 県名
    private String facilityName; // 施設名

    public BulkyGarbageFacility(float latitude, float longitude, String prefecture, String facilityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.prefecture = prefecture;
        this.facilityName = facilityName;
    }
}
