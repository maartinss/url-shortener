package com.api.shortener.service;

import com.api.shortener.controller.dto.CreateUrlResponseDTO;
import com.api.shortener.controller.dto.UrlResponseDTO;
import com.api.shortener.entity.UrlEntity;
import com.api.shortener.exception.UrlNoLongerAvailable;
import com.api.shortener.exception.UrlNotFound;
import com.api.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final int WEEKS_TO_DELETE = 2;

    private final UrlRepository urlRepository;

    private final AnalyticsProducerService analyticsService;


    public CreateUrlResponseDTO shortenUrl(String url) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl(url);
        urlEntity.setExpiryDate(LocalDateTime.now().plusWeeks(WEEKS_TO_DELETE));
        urlRepository.save(urlEntity);

        return new CreateUrlResponseDTO(urlEntity.getId());
    }

    @Cacheable(value = "URL_CACHE", key = "#id")
    public UrlResponseDTO getUrl(Long id) {
        UrlEntity url = urlRepository.findById(id).orElseThrow(() -> new UrlNotFound("There was no url for such id"));

        analyticsService.sendAnalytics(url.getId(), LocalDateTime.now());

        if(LocalDateTime.now().isAfter(url.getExpiryDate())) {
            urlRepository.delete(url);
            throw new UrlNoLongerAvailable("The link is expired");
        }

        return new UrlResponseDTO(url.getUrl());
    }
}
