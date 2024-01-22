package com.engx.engxserver.repository;

import com.engx.engxserver.entity.UnitRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnitRecordRepository extends JpaRepository<UnitRecord, Long> {
    @Query(value = "select * from unit_records where user_id = ?1", nativeQuery = true)
    List<UnitRecord> findAllByUser(Long userId);
}
