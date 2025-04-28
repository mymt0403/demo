package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacility;
import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.Prefecture;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import com.example.demo.repository.PrefectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MapsController {
    @Value("${api.key}")
    private String apiKey;
    @Value("${default.tokyo-lat}")
    private Float tokyoLatitude;
    @Value("${default.tokyo-long}")
    private Float tokyoLongitude;

    @Autowired
    BulkyGarbageFacilityRepository bulkyGarbageFacilityRepository;

    @Autowired
    PrefectureRepository prefectureRepository;

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "index";
    }

    @GetMapping("/api/center/{id}")
    public ResponseEntity<List<Float>> fetchCenter(Model model, @PathVariable("id") int id) throws SQLException {
        Optional<Prefecture> prefecture = prefectureRepository.findById(id);
        List<Float> centerPosition = new ArrayList<>();
        prefecture.ifPresentOrElse(x ->{
            centerPosition.add(x.getDefaultLatitude());
            centerPosition.add(x.getDefaultLongitude());
        }, () -> {
            // 値が取得できなかった場合は東京の座標を返す
            centerPosition.add(tokyoLatitude);
            centerPosition.add(tokyoLongitude);
        });
        return ResponseEntity.ok(centerPosition);
    }

    @GetMapping("/api/data/{id}")
    public ResponseEntity<List<BulkyGarbageFacilityDTO>> fetchData(Model model, @PathVariable("id") int id) throws SQLException {
        List<BulkyGarbageFacility> facilities = bulkyGarbageFacilityRepository.findByPrefectureNo(id);
        List<BulkyGarbageFacilityDTO> dataList = new ArrayList<>();
        for(BulkyGarbageFacility facility : facilities) {
            BulkyGarbageFacilityDTO bulkyGarbageFacilityDTO = new BulkyGarbageFacilityDTO();
            bulkyGarbageFacilityDTO.setLatitude(facility.getLatitude());
            bulkyGarbageFacilityDTO.setLongitude(facility.getLongitude());
            bulkyGarbageFacilityDTO.setPrefectureName(facility.getPrefecture().getPrefectureName());
            bulkyGarbageFacilityDTO.setFacilityName(facility.getFacilityName());
            dataList.add(bulkyGarbageFacilityDTO);
        }
        return ResponseEntity.ok(dataList);
    }
}
