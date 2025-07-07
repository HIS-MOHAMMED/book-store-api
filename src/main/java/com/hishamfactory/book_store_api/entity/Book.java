package com.hishamfactory.book_store_api.entity;

import jakarta.persistence.Entity;
@Entity
public class Book {
    private double id;
    private String title;
    private String author;
    private float price;
}
