package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacility;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
public class MapsController {
    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "index";
    }

    @GetMapping("/api/center/{id}")
    public ResponseEntity<List<Float>> fetchCenter(Model model, @PathVariable("id") int id) throws SQLException {
        Connection conn = BulkyGarbageFacilityRepository.conn();
        List<Float> centerPosition = BulkyGarbageFacilityRepository.fetchCenter(conn, id);
        return ResponseEntity.ok(centerPosition);
    }

    @GetMapping("/api/data/{id}")
    public ResponseEntity<List<BulkyGarbageFacility>> display(Model model, @PathVariable("id") int id) throws SQLException {
        Connection conn = BulkyGarbageFacilityRepository.conn();
        List<BulkyGarbageFacility> dataList = BulkyGarbageFacilityRepository.fetchFacilities(conn, id);
        return ResponseEntity.ok(dataList);
    }
}
