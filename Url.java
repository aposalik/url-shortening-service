package com.urlshortener.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    @Column(nullable = false, unique = true, length = 10)
    private String shortCode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int accessCount = 0;

    // ✏️ YOUR TASK: Complete the constructor
    public Url(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.createdAt = LocalDateTime.now();  // hint: LocalDateTime.now()
        this.updatedAt = LocalDateTime.now();
        this.accessCount = getAccessCount();
    }

    public Url() {
    }

    // ✏️ YOUR TASK: Complete ALL getters and setters below

    public Long getId() { return ???; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { originalUrl = originalUrl; }

    public String getShortCode() { return shortCode; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { updatedAt = updatedAt;}

    public int getAccessCount() { return accessCount; }
    public void setAccessCount(int accessCount) { accessCount = accessCount;}
}
