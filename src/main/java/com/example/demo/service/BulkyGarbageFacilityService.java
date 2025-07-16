package com.example.demo.service;

import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.BulkyGarbageFacilityInsertDTO;

import java.util.List;

public interface BulkyGarbageFacilityService {
    List<BulkyGarbageFacilityDTO> searchFacilities(Integer id);

    void addFacility(BulkyGarbageFacilityInsertDTO dto);
}
