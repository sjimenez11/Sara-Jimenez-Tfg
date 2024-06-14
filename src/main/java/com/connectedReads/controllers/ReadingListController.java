package com.connectedReads.controllers;

import com.connectedReads.dtos.ReadingListRequestDto;
import com.connectedReads.dtos.ReadingListResponseDto;
import com.connectedReads.entities.ReadingListEntity;
import com.connectedReads.mappers.ReadingListMapper;
import com.connectedReads.services.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/readingList")
public class ReadingListController {
    @Autowired
    private ReadingListService readingListService;
    @Autowired
    private ReadingListMapper readingListMapper;

    //TODO como hacerlo para que llegue el userId
    @PostMapping("")
    public ResponseEntity<ReadingListResponseDto> createReadingList( @RequestBody ReadingListRequestDto readingListRequestDto ){
        ReadingListEntity readingListSaved = readingListService.createReadingList(readingListMapper.toModel(readingListRequestDto));
        return ResponseEntity.created(null).body(readingListMapper.toResponse(readingListSaved));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReadingListResponseDto>> getBooksByUserId( @PathVariable Long userId ) {
        return ResponseEntity.ok(readingListMapper.toResponse(readingListService.getReadingListByUserId(userId)));
    }

    @DeleteMapping("/{id}")
    public void deleteBookById( @PathVariable Long id ){
        readingListService.deleteReadingListById(id);
    }
}
