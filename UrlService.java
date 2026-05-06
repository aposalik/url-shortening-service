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

    // ✏️ YOUR TASK: Implement this method
    // Steps:
    // 1. Generate a shortCode
    // 2. Make sure it doesn't already exist (loop if it does)
    // 3. Create a new Url object
    // 4. Save it to the DB
    // 5. Return it
    public Url createShortUrl(String originalUrl) {
        ???
    }

    // ✏️ YOUR TASK: Implement this method
    // Steps:
    // 1. Find the Url by shortCode (return empty if not found)
    // 2. Increment accessCount
    // 3. Save the updated object
    // 4. Return it
    public Optional<Url> getOriginalUrl(String shortCode) {
        ???
    }

    // ✏️ YOUR TASK: Implement this method
    // Steps:
    // 1. Find by shortCode → if not found return empty
    // 2. Update originalUrl and updatedAt
    // 3. Save and return
    public Optional<Url> updateShortUrl(String shortCode, String newUrl) {
        ???
    }

    // ✏️ YOUR TASK: Implement this method
    // Steps:
    // 1. Find by shortCode → if not found return false
    // 2. Delete it
    // 3. Return true
    public boolean deleteShortUrl(String shortCode) {
        ???
    }

    // ✏️ YOUR TASK: Implement this method
    // Same as getOriginalUrl but WITHOUT incrementing accessCount
    public Optional<Url> getStats(String shortCode) {
        ???
    }

    // ✅ COMPLETE — short code generator we built together
    private String generateShortCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(62);
            shortCode.append(characters.charAt(randomIndex));
        }
        return shortCode.toString();
    }
}
