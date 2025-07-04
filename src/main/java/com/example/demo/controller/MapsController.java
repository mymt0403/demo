package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.Position;
import com.example.demo.service.BulkyGarbageFacilityService;
import com.example.demo.service.PrefectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MapsController {
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    BulkyGarbageFacilityService bulkyGarbageFacilityService;
    @Autowired
    PrefectureService prefectureService;

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "index";
    }

    @GetMapping("/api/center/{prefectureNo}")
    public ResponseEntity<Position> getCenterPosition(Model model, @PathVariable("prefectureNo") int prefectureNo) {
        Position centerPosition = prefectureService.getCenterPosition(prefectureNo);
        return ResponseEntity.ok(centerPosition);
    }

    @GetMapping("/api/data/{prefectureNo}")
    public ResponseEntity<List<BulkyGarbageFacilityDTO>> searchFacilities(Model model, @PathVariable("prefectureNo") int prefectureNo) {
        List<BulkyGarbageFacilityDTO> dataList = bulkyGarbageFacilityService.searchFacilities(prefectureNo);
        return ResponseEntity.ok(dataList);
    }
}
