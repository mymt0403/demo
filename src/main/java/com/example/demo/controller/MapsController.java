package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacility;
import com.example.demo.service.BulkyGarbageFacilityService;
import com.example.demo.service.PrefectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MapsController {
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private BulkyGarbageFacilityService bulkyGarbageFacilityService;

    @Autowired
    private PrefectureService prefectureService;

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "index";
    }

    @GetMapping("/api/center/{prefectureNo}")
    public ResponseEntity<List<Float>> fetchCenter(@PathVariable("prefectureNo") int prefectureNo) throws SQLException {
        List<Float> centerPosition = prefectureService.fetchCenterPosition(prefectureNo);
        return ResponseEntity.ok(centerPosition);
    }

    @GetMapping("/api/data/{prefectureNo}")
    public ResponseEntity<List<BulkyGarbageFacility>> fetchData(@PathVariable("prefectureNo") int prefectureNo) throws SQLException {
        List<BulkyGarbageFacility> dataList = bulkyGarbageFacilityService.fetchBulkyGarbageFacility(prefectureNo);
        return ResponseEntity.ok(dataList);
    }
}
