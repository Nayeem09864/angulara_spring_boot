package com.example.crudapplication3.repository;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    //@Autowired
    private BookRepository underTest;

    @Test
    void findBookByTitle() {
        //given
        Long id= 50L;
        Book book= new Book(id,"After Typo 4","Author Typo 4", BookType.FREE);
        underTest.save(book);
        //when
        Book expected= underTest.findBookByTitle(book.getTitle());
        //then
        boolean flag=true;
        //assertThat(expected,book);

    }

    @Test
    void addBookByCheckingTitle() {
    }

    @Test
    void getBookByType() {
    }
}