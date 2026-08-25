package com.api.shortener.service;

import com.api.shortener.controller.dto.CreateUrlResponseDTO;
import com.api.shortener.controller.dto.UrlResponseDTO;
import com.api.shortener.entity.UrlEntity;
import com.api.shortener.repository.UrlRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    private static final Long MOCK_ID = 1L;

    private static final String MOCK_URL = "https://example.com";

    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private AnalyticsProducerService analyticsService;

    @Test
    public void shorteningAnUrlShouldReturnAResponseDTO() {
        CreateUrlResponseDTO dto = urlService.shortenUrl(MOCK_URL);

        assertNotNull(dto);
    }

    @Test
    public void requestingAValidUrlShouldReturnAResponseUrl() {
        UrlEntity expected = new UrlEntity();
        expected.setId(MOCK_ID);
        expected.setUrl(MOCK_URL);
        expected.setExpiryDate(LocalDateTime.MAX);
        
        when(urlRepository.findById(MOCK_ID)).thenReturn(Optional.of(expected));

        UrlResponseDTO dto = urlService.getUrl(MOCK_ID);


        assertNotNull(dto);
        assertEquals(expected.getUrl(), dto.url());
    }
}
