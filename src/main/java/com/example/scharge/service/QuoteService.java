package com.example.scharge.service;

import com.example.scharge.dto.RateDto;
import com.example.scharge.entity.Quote;
import com.example.scharge.entity.Rates;
import com.example.scharge.repository.QuoteRepository;
import com.example.scharge.repository.RateRepository;
import com.example.scharge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;
    private final RateRepository rateRepository;

    public QuoteService(UserRepository userRepository, QuoteRepository quoteRepository, RateRepository rateRepository) {
        this.userRepository = userRepository;
        this.quoteRepository = quoteRepository;
        this.rateRepository = rateRepository;
    }

    public Quote randomQuote() {
        return quoteRepository.randomQuote();
    }

    public List<Quote> allQuotes() {
        return quoteRepository.findAll();
    }

    public void likeQuote(Long quoteId) {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if(quote.isPresent()){
           quote.get().incrementLikes();
           quoteRepository.save(quote.get());
        }
    }

    public void addRate(Long quoteId, RateDto rateDto) {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if (quote.isPresent()){
            Rates newRate = new Rates();
            newRate.setRate(rateDto.getRate());
            newRate.setRateDescription(rateDto.getRateDescription());
            //supposing user is taken from security context and therefore exists
            newRate.setUser(userRepository.findById(rateDto.getUserId()).get());
            newRate.setQuote(quote.get());
            rateRepository.save(newRate);
            riCalculateQuoteRate(quote.get());
        }
    }

    private void riCalculateQuoteRate(Quote quote) {
        quote.averageRate(rateRepository.findAverageRateByQuoteId(quote.getId()));
        quoteRepository.save(quote);
    }

    public List<Quote> topRated() {
        return quoteRepository.findAllByOrderByRatingDesc();
    }

    public List<Quote> comparableQuote(String author, Long currentQuoteId) {
        return quoteRepository.findComparableQuotesByAuthorAndIdNot(author, currentQuoteId);
    }
}
