package com.engx.engxserver.repository;

import com.engx.engxserver.entity.Word;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(value = "select * from words where unit_id = ?1", nativeQuery = true)
    List<Word> findAllByUnit(Long unitId);
}
