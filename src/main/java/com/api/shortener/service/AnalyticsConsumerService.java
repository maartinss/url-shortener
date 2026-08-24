package com.api.shortener.service;

import com.api.shortener.controller.dto.UrlAccessEventDTO;
import com.api.shortener.entity.AnalyticEntity;
import com.api.shortener.entity.UrlEntity;
import com.api.shortener.repository.AnalyticsRepository;
import com.api.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsConsumerService {

    private final String TOPIC = "analytics";

    private final AnalyticsRepository analyticsRepository;

    private final UrlRepository urlRepository;

    @KafkaListener(topics = TOPIC)
    public void receiveAnalytics(ConsumerRecord<String, UrlAccessEventDTO> record) {
        UrlEntity url = urlRepository.findById(Long.valueOf(record.key())).orElseThrow(() -> new RuntimeException("url not found"));
        UrlAccessEventDTO event = record.value();

        AnalyticEntity analyticEntity = new AnalyticEntity();
        analyticEntity.setUrl(url);
        analyticEntity.setAccessedAt(event.accessedAt());
        analyticsRepository.save(analyticEntity);
    }
}
