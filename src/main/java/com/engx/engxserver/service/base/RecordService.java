package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AddBookRecordRequestDTO;
import com.engx.engxserver.dto.AddUnitRecordRequestDTO;
import com.engx.engxserver.dto.BookRecordDTO;
import com.engx.engxserver.dto.UnitRecordDTO;
import java.util.List;

public interface RecordService {
    BookRecordDTO addOneBookRecord(AddBookRecordRequestDTO addBookRecordRequestDTO);

    UnitRecordDTO addOneUnitRecord(AddUnitRecordRequestDTO addUnitRecordRequestDTO);

    List<BookRecordDTO> getBookRecordsOfUser(Long userId);

    List<UnitRecordDTO> getUnitRecordsOfUser(Long userId);
}
