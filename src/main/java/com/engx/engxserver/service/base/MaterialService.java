package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.BookDTO;
import com.engx.engxserver.dto.UnitDTO;
import com.engx.engxserver.dto.WordDTO;
import java.util.List;

public interface MaterialService {
    // UnitDTO addUnit(AddUnitRequestDTO chapter) throws InsertFailException;

    List<BookDTO> getAllBooksOfOwner(Long ownerId);

    List<UnitDTO> getAllUnitsOfBook(Long bookId);

    List<WordDTO> getAllWordsOfUnit(Long unitId);

    // UnitDTO getChapterById(Long id) throws ResourceNotFoundException;
}
