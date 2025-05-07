package com.example.demo.service;

import com.example.demo.model.Prefecture;
import com.example.demo.repository.PrefectureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrefectureServiceImplTest {

    @Mock
    private PrefectureRepository prefectureRepository;

    @InjectMocks
    private PrefectureServiceImpl prefectureService;

    @Value("${default.tokyo-lat}")
    private Float TOKYO_LAT;
    @Value("${default.tokyo-long}")
    private Float TOKYO_LONG;

    @Test
    public void fetchCenterPosition_prefectureExists() {
        // Arrange
        Prefecture prefecture = mock(Prefecture.class);
        when(prefecture.getDefaultLatitude()).thenReturn(43.06417f);
        when(prefecture.getDefaultLongitude()).thenReturn(141.34694f);
        when(prefectureRepository.findById(1)).thenReturn(Optional.of(prefecture));

        // Act
        List<Float> result = prefectureService.fetchCenterPosition(1);

        // Assert
        assertEquals(2, result.size());
        assertEquals(43.06417f, result.get(0));
        assertEquals(141.34694f, result.get(1));
    }

    @Test
    public void fetchCenterPosition_prefectureNotFound() {
        // Arrange
        when(prefectureRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        List<Float> result = prefectureService.fetchCenterPosition(999);

        // Assert
        assertEquals(2, result.size());
        assertEquals(TOKYO_LAT, result.get(0));
        assertEquals(TOKYO_LONG, result.get(1));
    }
}
