package com.kalpix.downloader.backend.dtos;

public class UrlRequest {
    private String urlName;

    public UrlRequest() {
    }

    public UrlRequest(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
