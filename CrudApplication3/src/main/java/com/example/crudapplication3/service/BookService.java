package com.example.crudapplication3.service;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(@PathVariable Long id);
    public Book getBookByTitle(@PathVariable String title);


    public Book addBook(@RequestBody Book book);

    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook);


    public void deleteBook(@PathVariable Long id);

    public Book addBookByCheckingTitle(@RequestBody Book book);

    public List<Book> getBookByType(@PathVariable BookType type);
}
