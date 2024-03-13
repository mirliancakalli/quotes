package com.example.quotes.service;

import com.example.quotes.dto.QuoteResponse;
import com.example.quotes.entity.Quote;
import com.example.quotes.entity.User;
import com.example.quotes.repository.QuoteRepository;
import com.example.quotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class LoadQuotesService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    @Value("${data.quote.api}")
    private String apiUrl;

    private final RestTemplate restTemplate;


    public LoadQuotesService(QuoteRepository quoteRepository, UserRepository userRepository, RestTemplate restTemplate) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchQuotes() {
        if (quoteRepository.countQuotes() == 0) {
            Objects.requireNonNull(restTemplate.getForObject(apiUrl, QuoteResponse.class))
                    .getQuotes()
                    .forEach(quoteDto -> {
                        Quote newQuote = new Quote();
                        newQuote.setQuote(quoteDto.getQuote());
                        newQuote.setAuthor(quoteDto.getAuthor());
                        quoteRepository.save(newQuote);
                    });
            if (userRepository.count() == 0) {
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
