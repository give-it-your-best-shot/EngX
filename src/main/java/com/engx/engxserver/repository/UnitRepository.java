package com.engx.engxserver.repository;

import com.engx.engxserver.entity.Unit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query(value = "select * from units where book_id = ?1", nativeQuery = true)
    List<Unit> findAllByBook(Long bookId);

    @Transactional
    @Modifying
    @Query(value = "insert into units (id, name, book_id) values (?1, ?2, ?3)", nativeQuery = true)
    void insertCSV(Long id, String name, Long bookId);
}
