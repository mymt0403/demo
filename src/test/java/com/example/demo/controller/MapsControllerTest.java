package com.example.demo.controller;

import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.Position;
import com.example.demo.service.BulkyGarbageFacilityService;
import com.example.demo.service.PrefectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MapsControllerTest {

    @InjectMocks
    private MapsController mapsController;

    @Mock
    private BulkyGarbageFacilityService bulkyGarbageFacilityService;

    @Mock
    private PrefectureService prefectureService;

    @Mock
    private Model model;

    private final String TEST_API_KEY = "test-api-key";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(mapsController, "apiKey", TEST_API_KEY);
    }

    @Test
    void init_returnsIndexView() {
        // Act
        String result = mapsController.init(model);

        // Assert
        verify(model).addAttribute("apiKey", TEST_API_KEY);
        assertEquals("index", result);
    }

    @Test
    void fetchCenterPosition_returnsCenterPositionFromService() {
        // Arrange
        int prefectureNo = 1;
        Position mockCenterPosition = new Position(35.0f, 135.0f);
        when(prefectureService.getCenterPosition(prefectureNo)).thenReturn(mockCenterPosition);

        // Act
        ResponseEntity<Position> response = mapsController.getCenterPosition(model, prefectureNo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCenterPosition, response.getBody());
    }

    @Test
    void searchFacilities_returnFacilityListFromService() {
        // Arrange
        int prefectureNo = 1;
        BulkyGarbageFacilityDTO dto = new BulkyGarbageFacilityDTO();
        List<BulkyGarbageFacilityDTO> mockDataList = List.of(dto);
        when(bulkyGarbageFacilityService.searchFacilities(prefectureNo)).thenReturn(mockDataList);

        // Act
        ResponseEntity<List<BulkyGarbageFacilityDTO>> response = mapsController.searchFacilities(model, prefectureNo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDataList, response.getBody());
    }
}
