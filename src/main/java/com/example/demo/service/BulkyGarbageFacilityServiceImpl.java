package com.example.demo.service;

import com.example.demo.model.BulkyGarbageFacility;
import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//        List<BulkyGarbageFacilityDTO> dataList = new ArrayList<>();
//        for(BulkyGarbageFacility facility : facilities) {
//            BulkyGarbageFacilityDTO bulkyGarbageFacilityDTO = new BulkyGarbageFacilityDTO();
//            bulkyGarbageFacilityDTO.setLatitude(facility.getLatitude());
//            bulkyGarbageFacilityDTO.setLongitude(facility.getLongitude());
//            bulkyGarbageFacilityDTO.setPrefectureName(facility.getPrefecture().getPrefectureName());
//            bulkyGarbageFacilityDTO.setFacilityName(facility.getFacilityName());
//            BulkyGarbageFacilityDTO bulkyGarbageFacilityDTO = modelMapper.map(facility, BulkyGarbageFacilityDTO.class);
//            dataList.add(bulkyGarbageFacilityDTO);
//        }
        List<BulkyGarbageFacilityDTO> dataList = modelMapper.map(facilities, new TypeToken<List<BulkyGarbageFacilityDTO>>(){}.getType());

        dataList.forEach(d -> System.out.println(d.toString()));
        System.out.println(dataList);
        return dataList;
    }
}
