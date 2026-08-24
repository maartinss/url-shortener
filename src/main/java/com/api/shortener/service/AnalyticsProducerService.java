package com.api.shortener.service;

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

    private KafkaTemplate<String, AnalyticEntity>  kafkaTemplate;

    public void sendAnalytics(UrlEntity url, LocalDateTime accessedAt) {
        AnalyticEntity analyticEntity = new AnalyticEntity();
        analyticEntity.setUrl(url);
        analyticEntity.setAccessedAt(accessedAt);

        kafkaTemplate.send(TOPIC, analyticEntity);
    }
}
