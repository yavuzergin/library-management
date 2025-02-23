package org.yavuz.library.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yavuz.library.librarymanagement.DTO.RetrieveMemberBooksResponse;
import org.yavuz.library.librarymanagement.model.LibraryManagement;

import java.util.List;

@Repository
public interface LibraryManagementRepository extends JpaRepository<LibraryManagement, Long> {
    List<LibraryManagement> findByMember_Id(Long memberId);
    List<LibraryManagement> findByBook_Id(Long bookId);
    @Query("""
 select new org.yavuz.library.librarymanagement.DTO.RetrieveMemberBooksResponse(lm) from LibraryManagement lm where lm.member.id = :memberId
 """)
    List<RetrieveMemberBooksResponse> retrieveResponseByMemberId(@Param ("memberId") Long memberId);
    @Modifying
    @Query("""
update LibraryManagement lm set lm.returnDate = CURRENT_DATE where lm.member.id = :memberId and lm.book.id = :bookId
""")
    void returnBooks(@Param ("memberId") Long memberId, @Param ("bookId") Long bookId);
}
