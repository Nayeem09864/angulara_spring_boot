package com.example.crudapplication3.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import org.antlr.v4.runtime.misc.NotNull;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @NotEmpty(message = "Cannot be empty.")
    @NotNull(message = "Cannot be null.")
    private String title;

    @NotEmpty(message = "Cannot be empty.")
    @NotNull(message = "Cannot be null.")
    private String author;

    private BookType bookType;


    // Getters, setters, and constructors (including no-args constructor)
    public Book(Long id, String title, String author,BookType bookType) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookType=bookType;
    }

    public Book() {

    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
