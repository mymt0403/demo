package com.example.demo;

import lombok.Data;

@Data
public class BulkyGarbageFacility {
    private float latitude;
    private float longitude;
    private String prefecture;
    private String facilityName;

    public BulkyGarbageFacility(float latitude, float longitude, String prefecture, String facilityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.prefecture = prefecture;
        this.facilityName = facilityName;
    }
}
