package com.example.quotes.dto;

import lombok.Data;

@Data
public class QuoteDto {
    private Long id;
    private String quote;
    private String author;
}
