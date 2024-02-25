package com.example.scharge.repository;

import com.example.scharge.entity.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends JpaRepository<Rates,Long> {

    @Query("SELECT AVG(r.rate) FROM Rates r WHERE r.quote.id = :quoteId")
    Double findAverageRateByQuoteId(@Param("quoteId") Long quoteId);

}
