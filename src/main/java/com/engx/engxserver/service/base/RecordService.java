package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AddBookRecordRequestDTO;
import com.engx.engxserver.dto.AddUnitRecordRequestDTO;
import com.engx.engxserver.dto.BookRecordDTO;
import com.engx.engxserver.dto.UnitRecordDTO;

public interface RecordService {
    BookRecordDTO addOneBookRecord(AddBookRecordRequestDTO addBookRecordRequestDTO);

    UnitRecordDTO addOneUnitRecord(AddUnitRecordRequestDTO addUnitRecordRequestDTO);
}
