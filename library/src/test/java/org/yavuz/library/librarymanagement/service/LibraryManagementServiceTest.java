package org.yavuz.library.librarymanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.books.repository.BookRepository;
import org.yavuz.library.librarymanagement.DTO.GiveBookRequest;
import org.yavuz.library.librarymanagement.model.LibraryManagement;
import org.yavuz.library.librarymanagement.repository.LibraryManagementRepository;
import org.yavuz.library.members.model.Member;
import org.yavuz.library.members.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryManagementServiceTest {
    @Mock
    private LibraryManagementRepository libraryManagementRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private LibraryManagementService libraryManagementService;

    private static Member getMember() {
        return new Member(1L, "Hakan", "Haksız", "+905555555555", "hakanhaksiz@gmail.com", "Eskişehir", "Öğrenci");
    }

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

    private static GiveBookRequest getGiveBookRequest() {
        return new GiveBookRequest(1L, 2L, LocalDate.parse("2025-02-27"), 30);
    }

    @Test
    void giveBook() {
        Member member = getMember();
        Book book = getBook();
        GiveBookRequest giveBookRequest = getGiveBookRequest();

        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(libraryManagementRepository.save(any(LibraryManagement.class))).thenReturn(new LibraryManagement());

        LibraryManagement libraryManagement = libraryManagementService.giveBook(giveBookRequest);
        assertNotNull(libraryManagement);
        verify(memberRepository, times(1)).findById(member.getId());
        verify(bookRepository, times(1)).findById(book.getId());
        verify(libraryManagementRepository, times(1)).save(any(LibraryManagement.class));
    }

    @Test
    void returnBook() {
        Member member = getMember();
        Book book = getBook();
        libraryManagementService.returnBook(member.getId(), book.getId());
        verify(libraryManagementRepository, times(1)).returnBook(member.getId(), book.getId());
    }

    @Test
    void updateBorrowDuration() {
        Member member = new Member();
        member.setId(1L);
        Book book = new Book();
        book.setId(1L);
        LibraryManagement libraryManagement = new LibraryManagement(1L, member, book, LocalDate.parse("2025-02-27"), 30, LocalDate.parse("2025-03-27"), "Kitap kullanımda.");
        LibraryManagement lmDetails = new LibraryManagement();
        lmDetails.setBorrowDuration(60);
        when(libraryManagementRepository.findById(1L)).thenReturn(Optional.of(libraryManagement));
        when(libraryManagementRepository.save(libraryManagement)).thenReturn(libraryManagement);
        LibraryManagement result = libraryManagementService.updateBorrowDuration(1L, lmDetails);
        assertEquals(60, result.getBorrowDuration());
    }

    @Test
    void returnBookStatus() {
        Member member = new Member();
        member.setId(1L);
        Book book = new Book();
        book.setId(1L);
        LibraryManagement libraryManagement = new LibraryManagement(1L, member, book, LocalDate.parse("2025-02-27"),
                30, LocalDate.parse("2025-03-27"), "Kitap kullanımda.");
        LibraryManagement lm = new LibraryManagement();
        lm.setStatus("Kitap iade edildi.");
        //when(libraryManagementRepository.findById(1L)).thenReturn(Optional.of(libraryManagement));
        //when(libraryManagementRepository.save(libraryManagement)).thenReturn(libraryManagement);
        libraryManagementService.returnBookStatus(1L, 1L);
        verify(libraryManagementRepository, times(1)).updateBookAvailabilityStatus(1L, 1L);

    }
}