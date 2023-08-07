package com.example.crudapplication3.controller;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import com.example.crudapplication3.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class BookControllerTest {


    @Mock
    private BookRepository bookRepository;

    @LocalServerPort
    private int port;
    private String baseUrl="http://localhost";
    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository testH2Repository;

    @BeforeAll
    public static void init(){
        restTemplate=new RestTemplate();
    }
    @BeforeEach
    public void setUp(){
        baseUrl=baseUrl.concat(":").concat(port+"").concat("/api/books");
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void getBookByTitle() {
    }

    @Test
    void getBookByType() {
    }

    @Test
    void addBook() {
        Book book= new Book(1L,"Title Test","Author test", BookType.FREE);
        bookRepository.save(book);
        Book response= restTemplate.postForObject(baseUrl,book,Book.class);
        assertEquals("Title Test",response.getTitle());
        assertEquals(1,testH2Repository.findAll().size());
    }

    @Test
    void addBookByCheckingTitle() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}