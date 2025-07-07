package com.hishamfactory.book_store_api.entity;

import jakarta.persistence.Entity;

@Entity
public class Category {
    private double id;
    private String name;
    private String description;
}
