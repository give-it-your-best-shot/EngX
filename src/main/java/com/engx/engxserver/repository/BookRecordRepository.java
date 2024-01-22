package com.engx.engxserver.repository;

import com.engx.engxserver.entity.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {}
