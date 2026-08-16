package com.api.shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class UrlEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private LocalDateTime expiryDate;

}
