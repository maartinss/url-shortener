package com.api.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UrlNotFound extends UrlException {

    String detail;

    public UrlNotFound(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail(){
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Not Found");
        pd.setDetail(detail);

        return pd;
    }


}

