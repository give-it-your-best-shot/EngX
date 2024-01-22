package com.engx.engxserver.repository;

import com.engx.engxserver.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from books where owner_id = ?1", nativeQuery = true)
    List<Book> findAllByOwner(Long ownerId);
}
