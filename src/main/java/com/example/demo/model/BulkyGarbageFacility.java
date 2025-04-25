package com.example.demo.model;

import lombok.Value;

import java.io.Serializable;

@Value
public class BulkyGarbageFacility implements Serializable {
    float latitude; // 緯度
    float longitude; // 経度
    String prefecture; // 県名
    String facilityName; // 施設名
}
