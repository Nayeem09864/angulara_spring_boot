package com.example.crudapplication3.service;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import com.example.crudapplication3.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }


//    @Autowired
//    public void BookService(BookRepository bookRepository) {
//        this.bookRepository=bookRepository;
//    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            existingBook.get().setTitle(updatedBook.getTitle());
            existingBook.get().setAuthor(updatedBook.getAuthor());
            existingBook.get().setBookType(updatedBook.getBookType());
            return bookRepository.save(existingBook.get());
        }
        return null;
    }


    public void deleteBook( Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book getBookByTitle(@PathVariable String title) {
//        ArrayList<Book> bookList = bookRepository.findAll();
//        return bookList[0];
        Book book=bookRepository.findBookByTitle(title);
        if(book!=null) {
            return bookRepository.findBookByTitle(title);
        }
        else {
            return null;
        }

    }

    public Book addBookByCheckingTitle(@RequestBody Book book) {
        Book book1= bookRepository.addBookByCheckingTitle(book.getTitle());
        if(book1==null) {
            return bookRepository.save(book);
        }
        else {
            System.out.println("Already exists this title");
            return null;
        }
    }

    public List<Book> getBookByType(@PathVariable BookType type) {
        //System.out.println("Get book by type: "+bookRepository.getBookByType(type.name()));
        return bookRepository.getBookByType(type);
    }
}
