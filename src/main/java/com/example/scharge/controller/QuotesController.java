package com.example.scharge.controller;

import com.example.scharge.dto.RateDto;
import com.example.scharge.entity.Quote;
import com.example.scharge.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuotesController {


    private final QuoteService quoteService;

    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        return ResponseEntity.ok(quoteService.randomQuote());
    }

    @GetMapping("/all")
    public List<Quote> getAllQuote() {
        return quoteService.allQuotes();
    }

    @PutMapping("/like/{quoteId}")
    public void addLikeToQuote(@PathVariable Long quoteId) {
         quoteService.likeQuote(quoteId);
    }

    @PostMapping("/rate/{quoteId}")
    public void addRate(@PathVariable Long quoteId, @RequestBody RateDto rateDto) {
        quoteService.addRate(quoteId, rateDto);
    }

    @GetMapping("/top-rated")
    public List<Quote> topRated() {
        return quoteService.topRated();
    }

    @GetMapping("/comparable-quote")
    public List<Quote> comparableQuote(@RequestParam String author , @RequestParam Long currentQuoteId) {
        return quoteService.comparableQuote(author, currentQuoteId);
    }

}
