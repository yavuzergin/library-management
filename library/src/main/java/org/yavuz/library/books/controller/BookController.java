package org.yavuz.library.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.books.repository.BookRepository;
import org.yavuz.library.books.service.BookService;
import java.util.List;
import java.util.Map;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/book-api/")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping("/list-books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/get-book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }
    @PostMapping("/add-book")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id) {
       Map<String, Boolean> response = bookService.deleteBook(id);
       return ResponseEntity.ok(response);
    }
}
