package org.yavuz.library.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yavuz.library.books.repository.BookRepository;
import org.yavuz.library.librarymanagement.DTO.GiveBookRequest;
import org.yavuz.library.librarymanagement.DTO.RetrieveMemberBooksResponse;
import org.yavuz.library.librarymanagement.DTO.ReturnBookRequest;
import org.yavuz.library.librarymanagement.repository.LibraryManagementRepository;
import org.yavuz.library.members.repository.MemberRepository;
import org.yavuz.library.members.model.Member;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.librarymanagement.model.LibraryManagement;
import org.yavuz.library.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LibraryManagementService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LibraryManagementRepository libraryManagementRepository;

    public LibraryManagement giveBook(GiveBookRequest input) {
        final Member member = memberRepository.findById(input.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getMemberId() + "numaralı üye bulunamadı."));
        final Book book = bookRepository.findById(input.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getBookId() + "numaralı kitap bulunamadı."));
        final LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.setMember(member);
        libraryManagement.setBook(book);
        libraryManagement.setIssueDate(input.getIssueDate());
        libraryManagement.setBorrowDuration(input.getBorrowDuration());
        return libraryManagementRepository.save(libraryManagement);
    }
    public LibraryManagement returnBook(ReturnBookRequest input){
        final Member member = memberRepository.findById(input.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getMemberId() + "numaralı üye bulunamadı."));
        final Book book = bookRepository.findById(input.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getBookId() + "numaralı kitap bulunamadı."));
        final LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.setMember(member);
        libraryManagement.setBook(book);
        libraryManagement.setReturnDate(input.getReturnDate());
        libraryManagement.setStatus(input.getStatus());
        return libraryManagementRepository.save(libraryManagement);
    }
    public Map<String, String> whoTakeWhichBook() {
        List<LibraryManagement> booksandmembers = libraryManagementRepository.findAll();
        Map<String, String> bms = booksandmembers.stream()
                .collect(Collectors.groupingBy(
                        bm -> bm.getMember().getMemberFirstName() + " " + bm.getMember().getMemberLastName(),
                        Collectors.mapping(bm -> bm.getBook().getBookName() + " " + bm.getBook().getBookAuthor(), Collectors.joining(", "))
                ));
        return bms;
    }
    public List<RetrieveMemberBooksResponse> retrieveMemberBooks(Long id) {
        return libraryManagementRepository.retrieveResponseByMemberId(id);
    }
    @Transactional
    public void returnBooks(Long memberId, Long bookId){
        libraryManagementRepository.returnBooks(memberId, bookId);
    }
}
