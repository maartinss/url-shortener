package com.api.shortener.service;

import com.api.shortener.controller.dto.UrlAccessEventDTO;
import com.api.shortener.entity.AnalyticEntity;
import com.api.shortener.entity.UrlEntity;
import com.api.shortener.repository.AnalyticsRepository;
import com.api.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalyticsConsumerService {

    private final String TOPIC = "analytics";

    private final AnalyticsRepository analyticsRepository;

    private final UrlRepository urlRepository;

    @KafkaListener(topics = TOPIC, groupId = "analytics-group")
    public void receiveAnalytics(@Payload UrlAccessEventDTO dto, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        UrlEntity url = urlRepository.findById(Long.valueOf(key)).orElseThrow(() -> new RuntimeException("url not found"));

        AnalyticEntity analyticEntity = new AnalyticEntity();
        analyticEntity.setUrl(url);
        analyticEntity.setAccessedAt(dto.accessedAt());
        analyticsRepository.save(analyticEntity);
        log.info("Analytics saved for url: {}", url);
    }
}
