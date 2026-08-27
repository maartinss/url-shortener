package com.api.shortener.controller.dto;

import com.api.shortener.entity.UrlEntity;

import java.time.LocalDateTime;

public record AnalyticsResponseDTO(String url, LocalDateTime accessedAt) {

}
