package com.engx.engxserver.repository;

import com.engx.engxserver.entity.UnitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UnitRecordRepository extends JpaRepository<UnitRecord, Long> {
    @Modifying
    @Transactional
    @Query(
            value =
                    "INSERT INTO book_records (user_id, unit_id, num_question, score) VALUES (:userId, :unitId, :numQuestion, :score)",
            nativeQuery = true)
    int addOne(
            @Param("userId") String userId,
            @Param("unitId") String bookId,
            @Param("numQuestion") Integer numQuestion,
            @Param("score") Float score);
}
