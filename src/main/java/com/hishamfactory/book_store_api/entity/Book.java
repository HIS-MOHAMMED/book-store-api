package com.hishamfactory.book_store_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    @NotBlank(message = "title is required.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "author is required.")
    private String author;

    @Column()
    @Positive(message = "price must be greater than zero.")
    private float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
}
