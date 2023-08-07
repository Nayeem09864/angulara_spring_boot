package com.example.crudapplication3.repository;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void findBookByTitle() {
        //given
        Long id= 1L;
        Book book= new Book("After Typo 4","Author Typo 4", BookType.FREE);
        Book book2= new Book("Test Title","Test Author", BookType.FREE);
        underTest.save(book);
        underTest.save(book2);
        //when
        Book expected= underTest.findBookByTitle("Test Title");
        System.out.println(expected);
        //then
        boolean flag=true;
        //assertThat(expected).isTrue();
        //assertThat(expected).isTrue();
        //assertThat(expected).isNotNull();                    // Assert that the result is not nul
    }

    @Test
    void addBookByCheckingTitle() {
    }

    @Test
    void getBookByType() {
    }
}