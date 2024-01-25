package com.engx.engxserver.repository;

import com.engx.engxserver.entity.Word;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(value = "select * from words where unit_id = ?1", nativeQuery = true)
    List<Word> findAllByUnit(Long unitId);

    @Transactional
    @Modifying
    @Query(value = "insert into words (id, writing, unit_id, meaning) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertCSV(Long id, String writing, Long unitId, String meaning);
}
