package com.urlshortener.repository;

import com.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    // ✏️ YOUR TASK: Declare a method to find a Url by its shortCode
    // Hint: Spring Data JPA generates queries from method names
    // findBy + FieldName
    ???

    // ✏️ YOUR TASK: Declare a method to check if a shortCode already exists
    // Hint: existsBy + FieldName
    ???
}
