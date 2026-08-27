package com.api.shortener.controller;

import com.api.shortener.controller.dto.AnalyticsResponseDTO;
import com.api.shortener.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/analytics")
    public List<AnalyticsResponseDTO> getAnalytics() {
        return analyticsService.findAll();
    }
}
