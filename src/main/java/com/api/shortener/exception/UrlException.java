package com.api.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UrlException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal Server Error");

        return pd;
    }
}
