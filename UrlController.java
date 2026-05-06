package com.urlshortener.controller;

import com.urlshortener.dto.CreateUrlRequest;
import com.urlshortener.dto.UrlResponse;
import com.urlshortener.model.Url;
import com.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // ✅ COMPLETE — Create short URL
    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody CreateUrlRequest request) {
        if (request.getUrl() == null || request.getUrl().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Url url = urlService.createShortUrl(request.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponse.from(url));
    }

    // ✏️ YOUR TASK: Implement GET /shorten/{shortCode}
    // - Call service to get the URL
    // - If found → return 200 with UrlResponse
    // - If not found → return 404
    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getOriginalUrl(@PathVariable String shortCode) {
        if (shortCode == null || shortCode.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Url url = urlService.getOriginalUrl(String shortCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponse.from(url));
    }

    // ✏️ YOUR TASK: Implement PUT /shorten/{shortCode}
    // - Validate request body
    // - Call service to update
    // - If found and updated → return 200
    // - If not found → return 404
    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> updateShortUrl(@PathVariable String shortCode, @RequestBody CreateUrlRequest request) {
        if (shortCode == null || shortCode.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Url url = urlService.updateShortUrl(String shortCode, String request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponse.from(url));
    }

    // ✏️ YOUR TASK: Implement DELETE /shorten/{shortCode}
    // - Call service to delete
    // - If deleted → return 204 No Content
    // - If not found → return 404
    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String shortCode) {
        if (shortCode == null || shortCode.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Url url = urlService.
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponse.from(url));
    }

    // ✏️ YOUR TASK: Implement GET /shorten/{shortCode}/stats
    // - Call service to get stats
    // - If found → return 200 with UrlResponse (includes accessCount)
    // - If not found → return 404
    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlResponse> getStats(@PathVariable String shortCode) {
        ???
    }
}
