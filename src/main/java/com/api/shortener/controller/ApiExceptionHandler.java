package com.api.shortener.controller;

import com.api.shortener.exception.UrlException;
import com.api.shortener.exception.UrlNotFound;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UrlException.class)
    public ProblemDetail handleUrlException(UrlException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(UrlNotFound.class)
    public ProblemDetail handleUrlNotFound(UrlNotFound e) {
        return e.toProblemDetail();
    }
}
