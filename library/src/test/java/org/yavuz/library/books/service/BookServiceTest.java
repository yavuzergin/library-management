package org.yavuz.library.books.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.books.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;



    private static Book getBook() {
        Book book = new Book();
        book.setId(2L);
        book.setBookName("Üzerimize oynanan oyunlar");
        book.setBookAuthor("Selahattin");
        book.setNumberOfPages(1453);
        book.setBookType("Siyaset");
        book.setPublicationDate("2023-01-01");
        return book;
    }


    private static Book getBook2() {
        Book book2 = new Book();
        book2.setId(3L);
        book2.setBookName("Test");
        book2.setBookAuthor("Test");
        book2.setNumberOfPages(999);
        book2.setBookType("Test");
        book2.setPublicationDate("2021-01-01");
        return book2;
    }

    @Test
    void testGetBookWithId() {
        Book book = getBook();
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        Book result = bookService.getBook(2L);
        assertEquals(book, result);
    }

    @Test
    void testGetAllBook() {
        Book book = getBook();
        Book book2 = getBook2();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book, book2));
        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
        assertEquals("Üzerimize oynanan oyunlar", result.get(0).getBookName());
    }

    @Test
    void testAddBook() {
        Book book = getBook();
        when(bookRepository.save(book)).thenReturn(book);
        Book result = bookService.addBook(book);
        assertEquals(book, result);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    void testDeleteBook() {
        Book book = getBook();
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        bookService.deleteBook(2L);
    }
    @Test
    void testUpdateBook() {
        Book book = getBook();
        Book bookDetails = new Book();
        bookDetails.setId(2L);
        bookDetails.setBookName("Javayı Öğreneceğim İnş.");
        bookDetails.setBookAuthor("Yavuz");
        bookDetails.setNumberOfPages(636);
        bookDetails.setBookType("Eğitim");
        bookDetails.setPublicationDate("2025-01-01");
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        Book updatedBook = bookService.updateBook(2L, bookDetails);
        assertEquals("Javayı Öğreneceğim İnş.", updatedBook.getBookName());
        assertEquals("Yavuz", updatedBook.getBookAuthor());
        assertEquals(636, updatedBook.getNumberOfPages());
        assertEquals("Eğitim", updatedBook.getBookType());
        assertEquals("2025-01-01", updatedBook.getPublicationDate());
        verify(bookRepository, times(1)).save(book);

    }

}
