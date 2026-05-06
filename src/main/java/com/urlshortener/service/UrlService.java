package com.urlshortener.service;

import com.urlshortener.model.Url;
import com.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url createShortUrl(String originalUrl) {
        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (urlRepository.existsByShortCode(shortCode));
        return urlRepository.save(new Url(originalUrl, shortCode));
    }

    public Optional<Url> getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode).map(url -> {
            url.setAccessCount(url.getAccessCount() + 1);
            return urlRepository.save(url);
        });
    }

    public Optional<Url> updateShortUrl(String shortCode, String newUrl) {
        return urlRepository.findByShortCode(shortCode).map(url -> {
            url.setOriginalUrl(newUrl);
            url.setUpdatedAt(LocalDateTime.now());
            return urlRepository.save(url);
        });
    }

    public boolean deleteShortUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode).map(url -> {
            urlRepository.delete(url);
            return true;
        }).orElse(false);
    }

    public Optional<Url> getStats(String shortCode) {
        return urlRepository.findByShortCode(shortCode);
    }

    private String generateShortCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            shortCode.append(characters.charAt(random.nextInt(62)));
        }
        return shortCode.toString();
    }
}
