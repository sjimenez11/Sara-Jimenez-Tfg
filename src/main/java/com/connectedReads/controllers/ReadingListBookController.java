package com.connectedReads.controllers;

import com.connectedReads.dtos.ReadingListBookRequestDto;
import com.connectedReads.dtos.ReadingListBookResponseDto;
import com.connectedReads.entities.ReadingListBook;
import com.connectedReads.entities.enums.ReadingStatus;
import com.connectedReads.mappers.ReadingListBookMapper;
import com.connectedReads.services.ReadingListBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/readingListBook")
public class ReadingListBookController {
    @Autowired
    private ReadingListBookService readingListBookService;
    @Autowired
    private ReadingListBookMapper readingListBookMapper;

    @PostMapping
    public ResponseEntity<ReadingListBookResponseDto> addReadingListBook(
            @RequestBody ReadingListBookRequestDto rlbRequestDto
            ){
        ReadingListBook readingListBook = readingListBookService.addReadingListBook(rlbRequestDto);
        ReadingListBookResponseDto rlbResponseDto = readingListBookMapper.toResponseDto(readingListBook);
        return ResponseEntity.created(null).body(rlbResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingListBookResponseDto> updateReadingListBookStatus(
            @PathVariable Long id, @RequestBody ReadingStatus status
            ){
        ReadingListBook readingListBook = readingListBookService.updateReadingListBookStatus(id, status);
        ReadingListBookResponseDto rlbResponseDto = readingListBookMapper.toResponseDto(readingListBook);
        return ResponseEntity.ok(rlbResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReadingListById(@PathVariable Long id) {
        readingListBookService.deleteReadingListBookById(id);
        return ResponseEntity.noContent().build();
    }
}
