package com.example.crudapplication3.repository;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findBookByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book addBookByCheckingTitle(@Param("title")  String title);

    @Query("SELECT b FROM Book b WHERE b.bookType = :type")
    List<Book> getBookByType(@Param("type") BookType type);
}
