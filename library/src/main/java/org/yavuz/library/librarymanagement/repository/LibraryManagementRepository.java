package org.yavuz.library.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.library.librarymanagement.model.LibraryManagement;

import java.util.List;

@Repository
public interface LibraryManagementRepository extends JpaRepository<LibraryManagement, Long> {
    List<LibraryManagement> findByMember_Id(Long memberId);
    List<LibraryManagement> findByBook_Id(Long bookId);
}
