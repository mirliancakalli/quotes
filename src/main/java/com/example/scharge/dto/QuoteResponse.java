package com.example.scharge.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuoteResponse {
    private List<QuoteDto> quotes;
    private int total;
    private int skip;
    private int limit;
}
