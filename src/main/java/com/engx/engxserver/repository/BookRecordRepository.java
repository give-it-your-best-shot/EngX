package com.engx.engxserver.repository;

import com.engx.engxserver.entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {
    @Modifying
    @Transactional
    @Query(
            value =
                    "INSERT INTO book_records (user_id, book_id, num_question, score) VALUES (:userId, :bookId, :numQuestion, :score) RETURNING *",
            nativeQuery = true)
    BookRecord addOne(
            @Param("userId") String userId,
            @Param("bookId") String bookId,
            @Param("numQuestion") Integer numQuestion,
            @Param("score") Float score);
}
