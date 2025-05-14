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
@Table(name = "GARBAGE_TYPE")
public class GarbageType {
    @Id
    private int typeId; // 粗大ごみ分類ID
    @Column(name = "TYPE_NAME")
    private String typeName; // 粗大ごみ分類名

    @OneToMany(mappedBy = "garbageType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BulkyGarbageFacility> facilities = new ArrayList<>();
}
