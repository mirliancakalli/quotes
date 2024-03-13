package com.example.quotes.dto;

import lombok.Data;

@Data
public class RateDto {
    private String rateDescription;
    private int rate;
    private Long userId;
}
