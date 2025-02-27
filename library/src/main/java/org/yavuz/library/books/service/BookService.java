package org.yavuz.library.books.service;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.library.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id + " numaralı kitap bulunamadı."));
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Map<String, Boolean> deleteBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id + " numaralı kitap bulunamadı."));
        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id + " numaralı kitap bulunamadı."));
        book.setBookName(bookDetails.getBookName());
        book.setBookAuthor(bookDetails.getBookAuthor());
        book.setNumberOfPages(bookDetails.getNumberOfPages());
        book.setBookType(bookDetails.getBookType());
        book.setPublicationDate(bookDetails.getPublicationDate());
        return bookRepository.save(book);
    }


}
