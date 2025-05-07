package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.service.BulkyGarbageFacilityService;
import com.example.demo.service.PrefectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
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
    public ResponseEntity<List<Float>> fetchCenter(Model model, @PathVariable("prefectureNo") int prefectureNo) {
        List<Float> centerPosition = prefectureService.fetchCenterPosition(prefectureNo);
        return ResponseEntity.ok(centerPosition);
    }

    @GetMapping("/api/data/{prefectureNo}")
    public ResponseEntity<List<BulkyGarbageFacilityDTO>> fetchData(Model model, @PathVariable("prefectureNo") int prefectureNo) {
        List<BulkyGarbageFacilityDTO> dataList = bulkyGarbageFacilityService.fetchFacilities(prefectureNo);
        return ResponseEntity.ok(dataList);
    }
}
