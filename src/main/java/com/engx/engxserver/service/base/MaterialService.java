package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AddBookRequestDTO;
import com.engx.engxserver.dto.AddUnitRequestDTO;
import com.engx.engxserver.dto.AddWordRequestDTO;
import com.engx.engxserver.dto.BookDTO;
import com.engx.engxserver.dto.UnitDTO;
import com.engx.engxserver.dto.WordDTO;
import com.engx.engxserver.exception.InsertFailException;
import java.util.List;

public interface MaterialService {

    List<BookDTO> getAllBooksOfOwner(Long ownerId);

    List<UnitDTO> getAllUnitsOfBook(Long bookId);

    List<WordDTO> getAllWordsOfUnit(Long unitId);

    UnitDTO addUnit(AddUnitRequestDTO addUnitRequestDTO) throws InsertFailException;

    BookDTO addBook(AddBookRequestDTO addBookRequestDTO) throws InsertFailException;

    BookDTO getBookById(Long bookId);

    UnitDTO getUnitById(Long unitId);

    WordDTO addWord(AddWordRequestDTO addWordRequestDTO) throws InsertFailException;
}
