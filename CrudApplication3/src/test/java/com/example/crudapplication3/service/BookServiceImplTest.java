package com.example.crudapplication3.service;

import com.example.crudapplication3.entity.Book;
import com.example.crudapplication3.entity.BookType;
import com.example.crudapplication3.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    //@Autowired
    @Mock
    private BookRepository bookRepository;
    private BookServiceImpl bookServiceUnderTest;
    //private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        //autoCloseable= MockitoAnnotations.openMocks(this);
        bookServiceUnderTest=new BookServiceImpl(bookRepository);
    }

    @Test
    void testGetAllBooks() {
        bookServiceUnderTest.getAllBooks();
        verify(bookRepository).findAll();
    }

    @Test
    //@Disabled
    void testGetBookById() {
        bookServiceUnderTest.getBookById(1L);
        verify(bookRepository).findById(1L);
    }

    @Test
    void testAddBook() {
        Book book= new Book(1L,"Title Test","Author test", BookType.FREE);
//        bookServiceUnderTest.addBook(book);
//        verify(bookRepository).save(book);
        bookServiceUnderTest.addBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor= ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());
        Book bookCaptured= bookArgumentCaptor.getValue();
        System.out.println(bookCaptured);
        assertThat(bookCaptured).isEqualTo(book);
    }

    @Test
    //@Disabled
    void updateBook() {
//        Book book= new Book(1L,"Title Test","Author test", BookType.FREE);
//        bookServiceUnderTest.addBook(book);
//        //verify(bookRepository).save(book);
//        Book updatedBook= new Book(1L,"Title Test Updated","Author test", BookType.FREE);
//        bookServiceUnderTest.updateBook(1L,updatedBook);
//        //System.out.println();
//        verify(bookRepository).save(updatedBook);

        Long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Existing Title");
        existingBook.setAuthor("Existing Author");
        existingBook.setBookType(BookType.FREE);

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setBookType(BookType.PREMIUM);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        // Act
        Book result = bookServiceUnderTest.updateBook(bookId, updatedBook);

        // Assert
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(existingBook);

        assertEquals(updatedBook.getTitle(), existingBook.getTitle());
        assertEquals(updatedBook.getAuthor(), existingBook.getAuthor());
        assertEquals(updatedBook.getBookType(), existingBook.getBookType());

        //verify(bookRepository,never()).save(updatedBook);
        assertNotNull(result);
        assertEquals(existingBook, result);
    }
    @Test
    public void testUpdateBook_BookNotFound() {
        // Arrange
        Long nonExistentBookId = 123456L;
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setBookType(BookType.PREMIUM);

        when(bookRepository.findById(nonExistentBookId)).thenReturn(Optional.empty());

        // Act
        Book result = bookServiceUnderTest.updateBook(nonExistentBookId, updatedBook);

        // Assert
        verify(bookRepository, times(1)).findById(nonExistentBookId);
        verify(bookRepository, never()).save(any(Book.class)); // Ensure save is not called

        assertNull(result);
    }

    @Test
    //@Disabled
    void deleteBook() {
        Book book= new Book(1L,"Title Test","Author test", BookType.FREE);
        bookServiceUnderTest.addBook(book);
        bookServiceUnderTest.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    //@Disabled
    void getBookByTitle() {
        // Arrange
        String title = "Existing Title";
        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle(title);
        expectedBook.setAuthor("Sample Author");
        expectedBook.setBookType(BookType.FREE);

        when(bookRepository.findBookByTitle(title)).thenReturn(expectedBook);
        //when(bookRepository.save(expectedBook)).thenReturn(expectedBook);
        // Act
        Book result = bookServiceUnderTest.getBookByTitle(title);

        // Assert
        verify(bookRepository, times(1)).findBookByTitle(title);
        assertEquals(expectedBook, result);
    }

    @Test
    public void testGetBookByTitle_NotFound() {
        // Arrange
        String title = "Nonexistent Title";

        when(bookRepository.findBookByTitle(title)).thenReturn(null);

        // Act
        Book result = bookServiceUnderTest.getBookByTitle(title);

        // Assert
        verify(bookRepository, times(1)).findBookByTitle(title);
        assertNull(result);
    }

    @Test
    //@Disabled
    void addBookByCheckingTitle() {
//        Book book= new Book(1L,"Title Test","Author test", BookType.FREE);
//        bookServiceUnderTest.addBookByCheckingTitle(book);
//        verify(bookRepository).addBookByCheckingTitle("Title Test");

        String newTitle = "New Title";
        Book bookToAdd = new Book();
        bookToAdd.setTitle(newTitle);
        bookToAdd.setAuthor("Author");
        bookToAdd.setBookType(BookType.FREE);

        when(bookRepository.addBookByCheckingTitle(newTitle)).thenReturn(null);
        when(bookRepository.save(bookToAdd)).thenReturn(bookToAdd);

        // Act
        Book result = bookServiceUnderTest.addBookByCheckingTitle(bookToAdd);

        // Assert
        verify(bookRepository, times(1)).addBookByCheckingTitle(newTitle);
        verify(bookRepository, times(1)).save(bookToAdd);

        assertNotNull(result);
        assertEquals(newTitle, result.getTitle());
    }

    @Test
    public void testAddBookByCheckingTitle_TitleExists() {
        // Arrange
        String existingTitle = "Existing Title";
        Book bookToAdd = new Book();
        bookToAdd.setTitle(existingTitle);
        bookToAdd.setAuthor("Author");
        bookToAdd.setBookType(BookType.FREE);

        when(bookRepository.addBookByCheckingTitle(existingTitle)).thenReturn(bookToAdd);

        // Act
        Book result = bookServiceUnderTest.addBookByCheckingTitle(bookToAdd);

        // Assert
        verify(bookRepository, times(1)).addBookByCheckingTitle(existingTitle);
        verify(bookRepository, never()).save(any(Book.class)); // Ensure save is not called

        assertNull(result);
    }

    @Test
    //@Disabled
    void getBookByType() {
        Book book= new Book(1L,"Title Test","Author test", BookType.FREE);
        //BookType bookType=BookType.FREE;
        bookServiceUnderTest.getBookByType(BookType.FREE);
        verify(bookRepository).getBookByType(BookType.FREE);
    }
}