package com.api.shortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1")
public class UrlController {

    @PostMapping("/url")
    public ResponseEntity<?> shortenUrl(@RequestParam String url) {
        return ResponseEntity.ok().build();
    }
}
