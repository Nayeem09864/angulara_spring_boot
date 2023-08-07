package com.example.crudapplication3.repository;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        bookRepositoryUnderTest.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void findBookByTitle() {
//        //given
//        Long id= 1L;
//        Book book= new Book("After Typo 4","Author Typo 4", BookType.FREE);
//        Book book2= new Book("Test Title","Test Author", BookType.FREE);
//        underTest.save(book);
//        underTest.save(book2);
//        //when
//        Book expected= underTest.findBookByTitle("Test Title");
//        System.out.println(expected);
//        //then
//        boolean flag=true;
//        //assertThat(expected).isTrue();
//        //assertThat(expected).isTrue();
//        //assertThat(expected).isNotNull();

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Title");
        book.setAuthor("Sample Author");
        book.setBookType(BookType.FREE);

        bookRepositoryUnderTest.save(book);
        // Act
        Book foundBook = bookRepositoryUnderTest.findBookByTitle("Sample Title");

        // Assert
        //assertNotNull(foundBook);
        assertEquals("Sample Title", foundBook.getTitle());
        assertEquals("Sample Author", foundBook.getAuthor());
        assertEquals(BookType.FREE, foundBook.getBookType());
    }

//    @Test
//    public void testFindBookByTitle_NotFound() {
//
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Sample Title");
//        book.setAuthor("Sample Author");
//        book.setBookType(BookType.FREE);
//
//        bookRepositoryUnderTest.save(book);
//        // Act
//        Book foundBook = bookRepositoryUnderTest.findBookByTitle("Nonexistent Title");
//
//        // Assert
//        assertNull(foundBook);
//    }

    @Test
    void addBookByCheckingTitle() {
        // Arrange
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Existing Title");
        existingBook.setAuthor("Existing Author");
        existingBook.setBookType(BookType.FREE);
        bookRepositoryUnderTest.save(existingBook);

        // Act
        Book foundBook = bookRepositoryUnderTest.addBookByCheckingTitle("Existing Title");

        // Assert
        assertNotNull(foundBook);
        assertEquals("Existing Title", foundBook.getTitle());
        assertEquals("Existing Author", foundBook.getAuthor());
        assertEquals(BookType.FREE, foundBook.getBookType());
    }

    @Test
    void getBookByType() {
        // Arrange
        Book freeBook = new Book();
        freeBook.setTitle("Free Book");
        freeBook.setAuthor("Author A");
        freeBook.setBookType(BookType.FREE);

        Book premiumBook = new Book();
        premiumBook.setTitle("Premium Book");
        premiumBook.setAuthor("Author B");
        premiumBook.setBookType(BookType.PREMIUM);
        bookRepositoryUnderTest.save(freeBook);
        bookRepositoryUnderTest.save(premiumBook);

        // Act
        List<Book> freeBooks = bookRepositoryUnderTest.getBookByType(BookType.FREE);

        // Assert
        assertNotNull(freeBooks);
        assertEquals(1, freeBooks.size());
        assertEquals(freeBook.getTitle(), freeBooks.get(0).getTitle());
        assertEquals(freeBook.getAuthor(), freeBooks.get(0).getAuthor());
        assertEquals(BookType.FREE, freeBooks.get(0).getBookType());
    }
}