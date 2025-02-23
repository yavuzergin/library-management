package org.yavuz.library.books.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.library.books.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
