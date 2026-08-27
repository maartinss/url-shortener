package com.api.shortener.service;

import com.api.shortener.controller.dto.AnalyticsResponseDTO;
import com.api.shortener.repository.AnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public List<AnalyticsResponseDTO> findAll() {
        return analyticsRepository.findAll()
                .stream()
                .map(analyticEntity -> new AnalyticsResponseDTO(analyticEntity.getUrl().getUrl(), analyticEntity.getAccessedAt()))
                .toList();
    }
}
