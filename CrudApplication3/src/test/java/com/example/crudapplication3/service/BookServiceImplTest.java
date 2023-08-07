package com.example.crudapplication3.service;

import com.example.crudapplication3.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class BookServiceImplTest {
    //@Autowired
    @Mock
    private BookRepository bookRepository;
    private BookServiceImpl bookServiceUnderTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        bookServiceUnderTest=new BookServiceImpl(bookRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllBooks() {
        bookServiceUnderTest.getAllBooks();
        verify(bookRepository).findAll();
    }
    @Test
    @Disabled
    void getBookById() {
    }

    @Test
    @Disabled
    void addBook() {
    }
}