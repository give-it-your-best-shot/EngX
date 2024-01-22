package com.engx.engxserver.repository;

import com.engx.engxserver.entity.BookRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {
    @Query(value = "select * from book_records where user_id = ?1", nativeQuery = true)
    List<BookRecord> findAllByUser(Long userId);
}
