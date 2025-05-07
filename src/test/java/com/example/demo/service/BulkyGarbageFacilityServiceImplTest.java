package com.example.demo.service;

import com.example.demo.model.BulkyGarbageFacility;
import com.example.demo.model.BulkyGarbageFacilityDTO;
import com.example.demo.model.Prefecture;
import com.example.demo.repository.BulkyGarbageFacilityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BulkyGarbageFacilityServiceImplTest {
    @Mock
    private BulkyGarbageFacilityRepository bulkyGarbageFacilityRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BulkyGarbageFacilityServiceImpl service;

    @Test
    public void fetchFacilities_emptyList() {
        // Arrange
        Integer prefectureNo = 1;
        List<BulkyGarbageFacility> emptyEntityList = Collections.emptyList();
        List<BulkyGarbageFacilityDTO> emptyDtoList = Collections.emptyList();

        when(bulkyGarbageFacilityRepository.findByPrefectureNo(prefectureNo)).thenReturn(emptyEntityList);
        when(modelMapper.map(eq(emptyEntityList), any(Type.class))).thenReturn(emptyDtoList);

        // Act
        List<BulkyGarbageFacilityDTO> result = service.fetchFacilities(prefectureNo);

        // Assert
        assertTrue(result.isEmpty());
        verify(bulkyGarbageFacilityRepository).findByPrefectureNo(prefectureNo);
        verify(modelMapper).map(eq(emptyEntityList), any(Type.class));
    }

    @Test
    public void fetchFacilities_oneFacility() {
        // Arrange
        Integer prefectureNo = 2;

        BulkyGarbageFacility entity1 = createEntity(35.0f, 139.0f, "Shinjuku Eco Center", "Tokyo");
        List<BulkyGarbageFacility> entityList = List.of(entity1);

        BulkyGarbageFacilityDTO dto1 = createDTO(35.0f, 139.0f, "Shinjuku Eco Center", "Tokyo");
        List<BulkyGarbageFacilityDTO> dtoList = List.of(dto1);

        when(bulkyGarbageFacilityRepository.findByPrefectureNo(prefectureNo)).thenReturn(entityList);
        when(modelMapper.map(eq(entityList), any(Type.class))).thenReturn(dtoList);

        // Act
        List<BulkyGarbageFacilityDTO> result = service.fetchFacilities(prefectureNo);

        // Assert
        assertEquals(1, result.size());

        BulkyGarbageFacilityDTO resultDto = result.get(0);
        assertEquals(35.0f, resultDto.getLatitude());
        assertEquals(139.0f, resultDto.getLongitude());
        assertEquals("Tokyo", resultDto.getPrefectureName());
        assertEquals("Shinjuku Eco Center", resultDto.getFacilityName());

        verify(bulkyGarbageFacilityRepository).findByPrefectureNo(prefectureNo);
        verify(modelMapper).map(eq(entityList), any(Type.class));
    }

    @Test
    public void testFetchFacilities_multipleFacilities() {
        // Arrange
        Integer prefectureNo = 3;

        Prefecture prefecture = new Prefecture();
        prefecture.setPrefectureName("Osaka");

        BulkyGarbageFacility entity1 = createEntity(34.7f, 135.5f, "Osaka", "Osaka Facility A");
        BulkyGarbageFacility entity2 = createEntity(34.8f, 135.6f, "Osaka", "Osaka Facility B");
        List<BulkyGarbageFacility> entityList = List.of(entity1, entity2);

        BulkyGarbageFacilityDTO dto1 = createDTO(34.7f, 135.5f, "Osaka Facility A", "Osaka");
        BulkyGarbageFacilityDTO dto2 = createDTO(34.8f, 135.6f, "Osaka Facility B", "Osaka");
        List<BulkyGarbageFacilityDTO> dtoList = List.of(dto1, dto2);

        when(bulkyGarbageFacilityRepository.findByPrefectureNo(prefectureNo)).thenReturn(entityList);
        when(modelMapper.map(eq(entityList), any(Type.class))).thenReturn(dtoList);

        // Act
        List<BulkyGarbageFacilityDTO> result = service.fetchFacilities(prefectureNo);

        // Assert
        assertEquals(2, result.size());

        BulkyGarbageFacilityDTO resultDto1 = result.get(0);
        assertEquals(34.7f, resultDto1.getLatitude());
        assertEquals(135.5f, resultDto1.getLongitude());
        assertEquals("Osaka", resultDto1.getPrefectureName());
        assertEquals("Osaka Facility A", resultDto1.getFacilityName());

        BulkyGarbageFacilityDTO resultDto2 = result.get(1);
        assertEquals(34.8f, resultDto2.getLatitude());
        assertEquals(135.6f, resultDto2.getLongitude());
        assertEquals("Osaka", resultDto2.getPrefectureName());
        assertEquals("Osaka Facility B", resultDto2.getFacilityName());

        verify(bulkyGarbageFacilityRepository).findByPrefectureNo(prefectureNo);
        verify(modelMapper).map(eq(entityList), any(Type.class));
    }

    private BulkyGarbageFacility createEntity(float latitude, float longitude, String prefectureName, String facilityName) {
        Prefecture prefecture = new Prefecture();
        prefecture.setPrefectureName(prefectureName);

        return new BulkyGarbageFacility(latitude, longitude, prefecture, facilityName);
    }

    private BulkyGarbageFacilityDTO createDTO(float latitude, float longitude, String facilityName, String prefectureName) {
        BulkyGarbageFacilityDTO dto = new BulkyGarbageFacilityDTO();
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setFacilityName(facilityName);
        dto.setPrefectureName(prefectureName);
        return dto;
    }
}
