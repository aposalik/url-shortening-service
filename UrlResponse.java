package com.urlshortener.dto;

import com.urlshortener.model.Url;
import java.time.LocalDateTime;

public class UrlResponse {
    private Long id;
    private String url;
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int accessCount;

    // ✅ Factory method — converts Url model → UrlResponse
    public static UrlResponse from(Url url) {
        UrlResponse response = new UrlResponse();
        response.id = url.getId();
        response.url = url.getOriginalUrl();
        response.shortCode = url.getShortCode();
        response.createdAt = url.getCreatedAt();
        response.updatedAt = url.getUpdatedAt();
        response.accessCount = url.getAccessCount();
        return response;
    }

    // Getters
    public Long getId() { return id; }
    public String getUrl() { return url; }
    public String getShortCode() { return shortCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public int getAccessCount() { return accessCount; }
}
