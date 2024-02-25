package com.example.scharge.service;

import com.example.scharge.dto.QuoteDto;
import com.example.scharge.dto.QuoteResponse;
import com.example.scharge.entity.Quote;
import com.example.scharge.entity.User;
import com.example.scharge.repository.QuoteRepository;
import com.example.scharge.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadQuotesService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    private final String apiUrl = "https://dummyjson.com/quotes";
    private final RestTemplate restTemplate;


    public LoadQuotesService(QuoteRepository quoteRepository, UserRepository userRepository, RestTemplate restTemplate) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchQuotes() {
        Long hasAnyQuotes = quoteRepository.countQuotes();
        if(hasAnyQuotes != null && hasAnyQuotes==0){
            QuoteResponse quoteResponse = restTemplate.getForObject(apiUrl, QuoteResponse.class);
            if (quoteResponse != null && quoteResponse.getQuotes()!=null) {
                for (QuoteDto quote : quoteResponse.getQuotes()) {
                    Quote newQuote = new Quote();
                    newQuote.setQuote(quote.getQuote());
                    newQuote.setAuthor(quote.getAuthor());
                    quoteRepository.save(newQuote);
                }
                insertUser();
            }
        }
    }

    private void insertUser() {
        User user = new User();
        user.setName("test");
        user.setSurname("test");
        userRepository.save(user);
    }

}
