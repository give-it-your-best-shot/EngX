package com.engx.engxserver.repository;

import com.engx.engxserver.entity.Unit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query(value = "select * from units where book_id = ?1", nativeQuery = true)
    List<Unit> findAllByBook(Long bookId);
}
