package com.kalpix.downloader.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WebsiteNotFoundException extends RuntimeException {
    public WebsiteNotFoundException(String invalid_credentials) {
        super(invalid_credentials);
    }
}
