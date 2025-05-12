package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PREFECTURE")
public class Prefecture {
    @Id
    private Integer number; // 都道府県コード
    @Column(name = "PREFECTURE_NAME")
    private String prefectureName; // 県名
    @Column(name = "DEFAULT_LATITUDE")
    private float defaultLatitude; // 中央座標の緯度
    @Column(name = "DEFAULT_LONGITUDE")
    private float defaultLongitude; // 中央座標の経度

    // prefecture の1レコードに対して facility が複数レコード紐づく(1対多の関係)
    @OneToMany(mappedBy = "prefecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BulkyGarbageFacility> facilities = new ArrayList<>();
}
