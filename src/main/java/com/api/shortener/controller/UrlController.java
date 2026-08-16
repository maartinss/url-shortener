package com.api.shortener.controller;

import com.api.shortener.controller.dto.ShortenRequest;
import com.api.shortener.controller.dto.UrlResponse;
import com.api.shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/url")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody ShortenRequest request) {
        String id = String.valueOf(urlService.shortenUrl(request.url()));
        return ResponseEntity.ok().body(new UrlResponse(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> redirectToUrl(@PathVariable String id) {
        String url = urlService.getUrl(Long.valueOf(id));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .headers(headers)
                .build();
    }
}
