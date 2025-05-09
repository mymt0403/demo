package com.example.demo.service;

import com.example.demo.entity.Prefecture;
import com.example.demo.model.Position;
import com.example.demo.repository.PrefectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

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

    private final Float TOKYO_LAT = 35.6816F;
    private final Float TOKYO_LONG = 139.7670F;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(prefectureService, "tokyoLatitude", TOKYO_LAT);
        ReflectionTestUtils.setField(prefectureService, "tokyoLongitude", TOKYO_LONG);
    }

    @Test
    public void getCenterPosition_prefectureExists() {
        // Arrange
        Prefecture prefecture = mock(Prefecture.class);
        when(prefecture.getDefaultLatitude()).thenReturn(43.06417f);
        when(prefecture.getDefaultLongitude()).thenReturn(141.34694f);
        when(prefectureRepository.findById(1)).thenReturn(Optional.of(prefecture));

        // Act
        Position result = prefectureService.getCenterPosition(1);

        // Assert
        assertEquals(43.06417f, result.getLatitude());
        assertEquals(141.34694f, result.getLongitude());
    }

    @Test
    public void getCenterPosition_prefectureNotFound() {
        // Arrange
        when(prefectureRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Position result = prefectureService.getCenterPosition(999);

        // Assert
        assertEquals(TOKYO_LAT, result.getLatitude());
        assertEquals(TOKYO_LONG, result.getLongitude());
    }
}
