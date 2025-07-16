package com.example.demo.service;

import com.example.demo.entity.BulkyGarbageFacility;
import com.example.demo.entity.GarbageType;
import com.example.demo.entity.Prefecture;
import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.BulkyGarbageFacilityInsertDTO;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BulkyGarbageFacilityServiceImpl implements BulkyGarbageFacilityService {
    private static final Logger logger = LoggerFactory.getLogger(BulkyGarbageFacilityServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BulkyGarbageFacilityRepository bulkyGarbageFacilityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BulkyGarbageFacilityDTO> searchFacilities(Integer prefectureNo) {
        List<BulkyGarbageFacility> facilities = bulkyGarbageFacilityRepository.findByPrefectureNo(prefectureNo);
        // 取得したデータをDTOに詰め替える
        return modelMapper.map(facilities, new TypeToken<List<BulkyGarbageFacilityDTO>>(){}.getType());
    }

    @Override
    @Transactional
    public void addFacility(BulkyGarbageFacilityInsertDTO dto) {
        BulkyGarbageFacility entity = new BulkyGarbageFacility();
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setPrefecture(new Prefecture(dto.getPrefectureNumber(), "null", 0F, 0F, new ArrayList<>()));
        entity.setFacilityName(dto.getFacilityName());
        entity.setAddress(dto.getAddress());
        entity.setMapUrl(dto.getMapUrl());
        entity.setGarbageType(new GarbageType(dto.getGarbageTypeId(), "", new ArrayList<>()));

        entityManager.persist(entity);
    }
}
