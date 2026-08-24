package com.api.shortener.service;

import com.api.shortener.controller.dto.UrlAccessEventDTO;
import com.api.shortener.entity.AnalyticEntity;
import com.api.shortener.entity.UrlEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnalyticsProducerService {

    private static final String TOPIC = "analytics";

    private final KafkaTemplate<String, UrlAccessEventDTO>  kafkaTemplate;

    public void sendAnalytics(Long urlId, LocalDateTime accessedAt) {
        UrlAccessEventDTO urlAccessEventDTO = new UrlAccessEventDTO(
                urlId,
                accessedAt
        );

        kafkaTemplate.send(TOPIC, String.valueOf(urlId), urlAccessEventDTO);
    }
}
