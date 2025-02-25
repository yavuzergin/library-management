package org.yavuz.library.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yavuz.library.librarymanagement.DTO.RetrieveMemberBooksResponse;
import org.yavuz.library.librarymanagement.model.LibraryManagement;
import org.yavuz.library.librarymanagement.DTO.GiveBookRequest;
import org.yavuz.library.librarymanagement.service.LibraryManagementService;

import java.util.List;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/library-api/")
public class LibraryManagementController {
    @Autowired
    private LibraryManagementService libraryManagementService;

    @PostMapping("/give-book")
    public LibraryManagement giveBook(@RequestBody GiveBookRequest input) {
        return libraryManagementService.giveBook(input);
    }

    @PostMapping("/return-book/{memberId}/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        libraryManagementService.returnBookStatus(memberId, bookId);
        libraryManagementService.returnBook(memberId, bookId);
        return ResponseEntity.ok("Kitap iade edildi.");
    }

    @PutMapping("/update-borrow-duration/{id}")
    public ResponseEntity<LibraryManagement> updateBookBorrowDuration(@PathVariable Long id, @RequestBody LibraryManagement borrowDurationDetails) {
        LibraryManagement borrowDuration = libraryManagementService.updateBorrowDuration(id, borrowDurationDetails);
        return ResponseEntity.ok(borrowDuration);
    }

    @GetMapping("/retrieve-member-books/{id}")
    public List<RetrieveMemberBooksResponse> retrieveMemberBooks(@PathVariable Long id) {
        return libraryManagementService.retrieveMemberBooks(id);
    }

}

    /*
    @GetMapping("/return-book-status/{memberId}/{bookId}")
    public ResponseEntity<String> returnBookStatus(@PathVariable Long memberId, @PathVariable Long bookId){
        libraryManagementService.returnBookStatus(memberId, bookId);
        return ResponseEntity.ok("Kitap durumu güncellendi.");
    }

    /*@PostMapping("/return-book")
    public LibraryManagement returnBook(@RequestBody ReturnBookRequest input) {
        libraryManagementService.returnBookStatus(input.getMemberId(), input.getBookId());
        return libraryManagementService.returnBook(input);
    }
    @GetMapping("/who-take-which-book")
    public Map<String, String> whoTakeWhichBook(){
        return libraryManagementService.whoTakeWhichBook();
    }
       */
