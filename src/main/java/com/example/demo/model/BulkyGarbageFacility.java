package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BULKY_GARBAGE_FACILITIES")
@IdClass(BulkyGarbageFacilityPK.class)
public class BulkyGarbageFacility {
    @Id
    private float latitude; // 緯度
    @Id
    private float longitude; // 経度

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREFECTURE_NO")
    private Prefecture prefecture;

    @Column(name = "FACILITY_NAME")
    private String facilityName; // 施設名

    @Override
    public String toString() {
        return "BulkyGarbageFacility{" +
                    "latitude=" + latitude + "," +
                    "longitude=" + longitude + "," +
                    "prefecture=Prefecture{" +
                        "prefectureName=" + prefecture.getPrefectureName() +
                    "}" + "," +
                    "facilityName=" + facilityName +
                "}";
    }
}
