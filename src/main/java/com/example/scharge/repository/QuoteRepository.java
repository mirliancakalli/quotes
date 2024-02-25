package com.example.scharge.repository;

import com.example.scharge.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote,Long> {


    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote randomQuote();

    List<Quote> findAllByOrderByRatingDesc();

    @Query(value = "SELECT COUNT(*) FROM quotes", nativeQuery = true)
    Long countQuotes();
}
