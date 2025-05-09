package com.example.demo.service;

import com.example.demo.entity.BulkyGarbageFacility;
import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulkyGarbageFacilityServiceImpl implements BulkyGarbageFacilityService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BulkyGarbageFacilityRepository bulkyGarbageFacilityRepository;

    @Override
    public List<BulkyGarbageFacilityDTO> fetchFacilities(Integer prefectureNo) {
        List<BulkyGarbageFacility> facilities = bulkyGarbageFacilityRepository.findByPrefectureNo(prefectureNo);
        // 取得したデータをDTOに詰め替える
        return modelMapper.map(facilities, new TypeToken<List<BulkyGarbageFacilityDTO>>(){}.getType());
    }
}
