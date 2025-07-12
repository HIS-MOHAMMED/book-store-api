package com.hishamfactory.book_store_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Book> books;
}
