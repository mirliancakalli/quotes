package com.example.scharge;

import com.example.scharge.service.LoadQuotesService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuotesApplication {

	private final LoadQuotesService loadQuotesService;

	public QuotesApplication(LoadQuotesService loadQuotesService) {
		this.loadQuotesService = loadQuotesService;
	}

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}

	@PostConstruct
	public void init() {
		loadQuotesService.fetchQuotes();
	}

}
