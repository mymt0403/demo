package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BulkyGarbageFacilityPK implements Serializable {
    @Column(name = "LATITUDE")
    private float latitude; // 緯度
    @Column(name = "LONGITUDE")
    private float longitude; // 経度
}
