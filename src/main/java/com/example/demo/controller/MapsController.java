package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.BulkyGarbageFacilityInsertDTO;
import com.example.demo.model.Position;
import com.example.demo.service.BulkyGarbageFacilityService;
import com.example.demo.service.PrefectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private static final Logger logger = LoggerFactory.getLogger(MapsController.class);

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

    @PostMapping("/api/data")
    public ResponseEntity<String> addFacility(@RequestBody BulkyGarbageFacilityInsertDTO facility) {
        try {
            bulkyGarbageFacilityService.addFacility(facility);
            return ResponseEntity.ok("登録成功");
        } catch (DataIntegrityViolationException e) {
            logger.error("データベース制約違反が発生しました。", e);
            return ResponseEntity.badRequest().body("データベース制約違反が発生しました。");
        } catch (IllegalArgumentException e) {
            logger.error("入力値エラーが発生しました。: {}", e.getMessage());
            return ResponseEntity.badRequest().body("入力内容が正しくありません。");
        } catch (Exception e) {
            logger.error("予期しないエラーが発生しました", e);
            return ResponseEntity.badRequest().body("システムエラーが発生しました。");
        }
    }
}
