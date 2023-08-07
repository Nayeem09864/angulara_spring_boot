package com.example.crudapplication3.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface TestH2Repository extends JpaRepository<Book,Long> {
}
