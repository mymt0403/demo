package com.example.demo.model;

import lombok.Data;

@Data
public class BulkyGarbageFacilityInsertDTO {
    private float latitude;
    private float longitude;
    private int prefectureNumber;
    private String facilityName;
    private String address;
    private String mapUrl;
    private int garbageTypeId;
}
