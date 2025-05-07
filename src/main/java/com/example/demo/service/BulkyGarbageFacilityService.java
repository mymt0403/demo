package com.example.demo.service;

import com.example.demo.model.BulkyGarbageFacilityDTO;

import java.util.List;

public interface BulkyGarbageFacilityService {
    List<BulkyGarbageFacilityDTO> fetchFacilities(Integer id);
}
