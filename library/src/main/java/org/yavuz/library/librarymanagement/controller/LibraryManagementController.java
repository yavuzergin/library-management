package org.yavuz.library.librarymanagement.controller;

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
import org.yavuz.library.librarymanagement.model.LibraryManagement;
import org.yavuz.library.librarymanagement.DTO.ReturnBookRequest;
import org.yavuz.library.librarymanagement.DTO.GiveBookRequest;
import org.yavuz.library.librarymanagement.service.LibraryManagementService;
import org.yavuz.library.librarymanagement.repository.LibraryManagementRepository;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/return-book")
    public LibraryManagement returnBook(@RequestBody ReturnBookRequest input) {
        return libraryManagementService.returnBook(input);
    }
    @GetMapping("/who-take-which-book")
    public Map<String, String> whoTakeWhichBook(){
        return libraryManagementService.whoTakeWhichBook();
    }
}
