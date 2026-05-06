package com.urlshortener.controller;
import com.urlshortener.dto.CreateUrlRequest;
import com.urlshortener.dto.UrlResponse;
import com.urlshortener.model.Url;
import com.urlshortener.service.UrlService;
import org.springframework.http.HttpHeaders;
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

    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody CreateUrlRequest request) {
        if (request.getUrl() == null || request.getUrl().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Url url = urlService.createShortUrl(request.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponse.from(url));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getOriginalUrl(@PathVariable String shortCode) {
        return urlService.getOriginalUrl(shortCode)
                .map(url -> ResponseEntity.ok(UrlResponse.from(url)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> updateShortUrl(@PathVariable String shortCode, @RequestBody CreateUrlRequest request) {
        if (request.getUrl() == null || request.getUrl().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return urlService.updateShortUrl(shortCode, request.getUrl())
                .map(url -> ResponseEntity.ok(UrlResponse.from(url)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String shortCode) {
        return urlService.deleteShortUrl(shortCode)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        return urlService.getOriginalUrl(shortCode)
                .map(url -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Location", url.getOriginalUrl());
                    return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlResponse> getStats(@PathVariable String shortCode) {
        return urlService.getStats(shortCode)
                .map(url -> ResponseEntity.ok(UrlResponse.from(url)))
                .orElse(ResponseEntity.notFound().build());
    }
}
