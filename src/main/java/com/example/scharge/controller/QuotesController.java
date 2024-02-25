package com.example.scharge.controller;

import com.example.scharge.dto.RateDto;
import com.example.scharge.entity.Quote;
import com.example.scharge.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Quotes", description = "Quotes service Api")
@RestController
@RequestMapping("/api/quotes")
public class QuotesController {


    private final QuoteService quoteService;

    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Operation(
            summary = "Random Quote",
            description = "Retrieve a random quote from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful retrieve")
    })
    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        return ResponseEntity.ok(quoteService.randomQuote());
    }


    @Operation(
            summary = "All quotes",
            description = "Retrieve all available quotes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful retrieve")
    })
    @GetMapping("/all")
    public List<Quote> getAllQuote() {
        return quoteService.allQuotes();
    }


    @Operation(
            summary = "Add like to quote",
            description = "Add like to quote and additionally increase likes for that quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful insert")
    })
    @PutMapping("/like/{quoteId}")
    public void addLikeToQuote(@PathVariable Long quoteId) {
         quoteService.likeQuote(quoteId);
    }


    @Operation(
            summary = "Rate quote",
            description = "Rate a quote and recalculate rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful insert")
    })
    @PostMapping("/rate/{quoteId}")
    public void addRate(@PathVariable Long quoteId, @RequestBody RateDto rateDto) {
        quoteService.addRate(quoteId, rateDto);
    }


    @Operation(
            summary = "Top rated quotes",
            description = "Retrieve top rated quotes based on previously reported rating from users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful retrieval")
    })
    @GetMapping("/top-rated")
    public List<Quote> topRated() {
        return quoteService.topRated();
    }


    @Operation(
            summary = "Comparable quotes",
            description = "Get quotes from same author but different from previous one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful retrieval")
    })
    @GetMapping("/comparable-quote")
    public List<Quote> comparableQuote(@RequestParam String author , @RequestParam Long currentQuoteId) {
        return quoteService.comparableQuote(author, currentQuoteId);
    }

}
