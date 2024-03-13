package com.example.quotes;

import com.example.quotes.entity.Quote;
import com.example.quotes.entity.User;
import com.example.quotes.service.QuoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import com.example.quotes.dto.RateDto;
import com.example.quotes.entity.Rates;
import com.example.quotes.repository.QuoteRepository;
import com.example.quotes.repository.RateRepository;
import com.example.quotes.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QuoteServiceTest {

    @InjectMocks
    private QuoteService quoteService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private RateRepository rateRepository;

    @BeforeEach
    public void setUp() {
        // Set up any initial behavior
        Quote expectedQuote = new Quote();
        expectedQuote.setId(1L);
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(expectedQuote));
    }

    @Test
    public void testRandomQuote() {
        when(quoteRepository.randomQuote()).thenReturn(new Quote());

        Quote actualQuote = quoteService.randomQuote();

        Assertions.assertNotNull(actualQuote);
    }

    @Test
    public void testAllQuotes() {
        List<Quote> expectedQuotes = Arrays.asList(new Quote(), new Quote());

        doReturn(expectedQuotes).when(quoteRepository).findAll();

        List<Quote> actualQuotes = quoteService.allQuotes();

        Assertions.assertEquals(expectedQuotes.size(), actualQuotes.size());
        Assertions.assertTrue(actualQuotes.containsAll(expectedQuotes));
    }

    @Test
    public void testLikeQuote() {
        Long quoteId = 1L;
        Quote quote = new Quote();
        quote.setId(quoteId);
        quote.setLikes(0);
        when(quoteRepository.findById(quoteId)).thenReturn(Optional.of(quote));

        quoteService.likeQuote(quoteId);

        Assertions.assertEquals(1, quote.getLikes());
        verify(quoteRepository, times(1)).save(quote);
    }

    @Test
    public void testAddRate() {
        Long quoteId = 1L;
        RateDto rateDto = new RateDto();
        rateDto.setRate(5);
        rateDto.setRateDescription("Good");
        rateDto.setUserId(1L);
        Quote quote = new Quote();
        quote.setId(quoteId);
        when(quoteRepository.findById(quoteId)).thenReturn(Optional.of(quote));
        when(userRepository.findById(rateDto.getUserId())).thenReturn(Optional.of(new User()));

        quoteService.addRate(quoteId, rateDto);

        verify(rateRepository, times(1)).save(any(Rates.class));
        verify(quoteRepository, times(1)).save(quote);
    }

    @Test
    public void testTopRated() {
        List<Quote> expectedQuotes = Arrays.asList(new Quote(), new Quote());
        when(quoteRepository.findAllByOrderByRatingDesc()).thenReturn(expectedQuotes);

        List<Quote> actualQuotes = quoteService.topRated();

        Assertions.assertEquals(expectedQuotes.size(), actualQuotes.size());
        Assertions.assertTrue(actualQuotes.containsAll(expectedQuotes));
    }

    @Test
    public void testComparableQuote() {
        String author = "Author";
        Long currentQuoteId = 1L;
        List<Quote> expectedQuotes = Arrays.asList(new Quote(), new Quote());
        when(quoteRepository.findComparableQuotesByAuthorAndIdNot(author, currentQuoteId)).thenReturn(expectedQuotes);

        List<Quote> actualQuotes = quoteService.comparableQuote(author, currentQuoteId);

        Assertions.assertEquals(expectedQuotes.size(), actualQuotes.size());
        Assertions.assertTrue(actualQuotes.containsAll(expectedQuotes));
    }
}
