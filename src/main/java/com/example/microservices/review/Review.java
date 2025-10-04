package com.example.microservices.review;


import com.example.microservices.company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private Long id;
    private String title;
    private String description;
    private double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;


}
