package org.yavuz.library.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yavuz.library.books.repository.BookRepository;
import org.yavuz.library.librarymanagement.DTO.GiveBookRequest;
import org.yavuz.library.librarymanagement.DTO.RetrieveMemberBooksResponse;
import org.yavuz.library.librarymanagement.repository.LibraryManagementRepository;
import org.yavuz.library.members.repository.MemberRepository;
import org.yavuz.library.members.model.Member;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.librarymanagement.model.LibraryManagement;
import org.yavuz.library.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class LibraryManagementService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LibraryManagementRepository libraryManagementRepository;

    //Kitap verme işlemi.
    public LibraryManagement giveBook(GiveBookRequest input) {
        final Member member = memberRepository.findById(input.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getMemberId() + "numaralı üye bulunamadı."));

        final Book book = bookRepository.findById(input.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getBookId() + "numaralı kitap bulunamadı."));

        final String isBookAvailable = libraryManagementRepository.bookAvailabilityCheck(input.getBookId());
        if (isBookAvailable != null && isBookAvailable.equals("Kitap kullanımda.")) {
            throw new ResourceNotFoundException(input.getBookId() + " numaralı kitap başka bir üyenin kullanımında. " +
                    "Lütfen kitabın iade edilmesini bekleyiniz.");
        }
        final LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.setMember(member);
        libraryManagement.setBook(book);
        libraryManagement.setIssueDate(input.getIssueDate());
        libraryManagement.setBorrowDuration(input.getBorrowDuration());
        libraryManagement.setStatus("Kitap kullanımda.");
        return libraryManagementRepository.save(libraryManagement);
    }

    //Kitap iade işlemi.
    @Transactional
    public void returnBook(Long memberId, Long bookId) {
        libraryManagementRepository.returnBook(memberId, bookId);
    }

    //Kitabın kullanım süresini uzatma - kısaltma için güncelleme işlemi.
    public LibraryManagement updateBorrowDuration(Long id, LibraryManagement lmDetails) {
        LibraryManagement lm = libraryManagementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " numaralı işlem bulunamadı."));
        lm.setBorrowDuration(lmDetails.getBorrowDuration());
        return libraryManagementRepository.save(lm);
    }

    //Üye hangi kitabı kullanıyor?
    public List<RetrieveMemberBooksResponse> retrieveMemberBooks(Long id) {
        return libraryManagementRepository.retrieveResponseByMemberId(id);
    }

    //Kitap iadesinde statusu güncelleme işlemi.
    @Transactional
    public void returnBookStatus(Long memberId, Long bookId) {
        libraryManagementRepository.updateBookAvailabilityStatus(memberId, bookId);
    }
}


 /* public LibraryManagement returnBook(ReturnBookRequest input) {
        final Member member = memberRepository.findById(input.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getMemberId() + "numaralı üye bulunamadı."));
        final Book book = bookRepository.findById(input.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(input.getBookId() + "numaralı kitap bulunamadı."));
        final LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.setMember(member);
        libraryManagement.setBook(book);
        libraryManagement.setReturnDate(input.getReturnDate());
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
      */

