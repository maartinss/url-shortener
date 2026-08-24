package com.api.shortener.controller.dto;

import java.time.LocalDateTime;

public record UrlAccessEventDTO(Long urlId, LocalDateTime accessedAt) {
}
