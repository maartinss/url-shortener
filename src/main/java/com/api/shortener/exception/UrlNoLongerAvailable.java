package com.api.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UrlNoLongerAvailable extends UrlException {

    String detail;

    public UrlNoLongerAvailable(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.GONE);
        pd.setTitle("Url could not be found");
        pd.setDetail(detail);

        return pd;
    }
}
