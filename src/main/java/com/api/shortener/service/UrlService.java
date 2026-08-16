package com.api.shortener.service;

import com.api.shortener.entity.UrlEntity;
import com.api.shortener.exception.UrlNoLongerAvailable;
import com.api.shortener.exception.UrlNotFound;
import com.api.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final int WEEKS_TO_DELETE = 2;

    private final UrlRepository urlRepository;

    public Long shortenUrl(String url) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl(url);
        urlEntity.setExpiryDate(LocalDateTime.now().plusWeeks(WEEKS_TO_DELETE));
        urlRepository.save(urlEntity);

        return urlEntity.getId();
    }

    public String getUrl(Long id) {
        UrlEntity url = urlRepository.findById(id).orElseThrow(() -> new UrlNotFound("There was no url for such id"));

        if(LocalDateTime.now().isAfter(url.getExpiryDate())) {
            urlRepository.delete(url);
            throw new UrlNoLongerAvailable("The link is expired");
        }

        return url.getUrl();
    }
}
