package com.example.demo.model;

import lombok.Data;

@Data
public class BulkyGarbageFacilityDTO {
    private float latitude; // 緯度
    private float longitude; // 経度
    private String prefectureName; // 県名
    private String facilityName; // 施設名
}
