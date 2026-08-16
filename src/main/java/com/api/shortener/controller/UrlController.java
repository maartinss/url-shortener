package com.api.shortener.controller;

import com.api.shortener.controller.dto.CreateUrlRequestDTO;
import com.api.shortener.controller.dto.CreateUrlResponseDTO;
import com.api.shortener.controller.dto.UrlResponseDTO;
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
    public ResponseEntity<CreateUrlResponseDTO> shortenUrl(@RequestBody CreateUrlRequestDTO request) {
        return ResponseEntity.ok().body(urlService.shortenUrl(request.url()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> redirectToUrl(@PathVariable String id) {
        UrlResponseDTO urlResponse = urlService.getUrl(Long.valueOf(id));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlResponse.url()));

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .headers(headers)
                .build();
    }
}
