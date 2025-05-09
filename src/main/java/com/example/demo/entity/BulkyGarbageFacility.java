package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BULKY_GARBAGE_FACILITIES")
@IdClass(BulkyGarbageFacilityPK.class)
public class BulkyGarbageFacility {
    @Id
    private float latitude; // 緯度
    @Id
    private float longitude; // 経度

    // facility の複数レコードに対して prefecture は1レコード紐づく(多対1の関係)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREFECTURE_NO")
    private Prefecture prefecture;

    @Column(name = "FACILITY_NAME")
    private String facilityName; // 施設名

    // 循環参照によるStackOverFlowを防止
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
