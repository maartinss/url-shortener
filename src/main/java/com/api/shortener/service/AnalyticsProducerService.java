package com.api.shortener.service;

import com.api.shortener.controller.dto.UrlAccessEventDTO;
import com.api.shortener.entity.AnalyticEntity;
import com.api.shortener.entity.UrlEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalyticsProducerService {

    private static final String TOPIC = "analytics";

    private final KafkaTemplate<String, UrlAccessEventDTO>  kafkaTemplate;

    public void sendAnalytics(Long urlId, LocalDateTime accessedAt) {
        log.info("Trying to send analytics to url {}", urlId);

        UrlAccessEventDTO urlAccessEventDTO = new UrlAccessEventDTO(
                urlId,
                accessedAt
        );

        kafkaTemplate.send(TOPIC, String.valueOf(urlId), urlAccessEventDTO).whenComplete((event, throwable) -> {
            if(throwable == null) log.info("Sending analytics to url {}", urlId);
            else log.error("Error sending analytics to url {}", urlId, throwable);
        });
    }
}
