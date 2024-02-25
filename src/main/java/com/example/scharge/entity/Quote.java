package com.example.scharge.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quote;

    private String author;

    private Double rating;

    private int likes;

    public void incrementLikes() {
        this.likes++;
    }

    public void averageRate(Double rating) {
        this.rating = rating;
    }
}
