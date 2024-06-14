package com.connectedReads.controllers;

import com.connectedReads.dtos.ReadingListRequestDto;
import com.connectedReads.dtos.ReadingListResponseDto;
import com.connectedReads.entities.ReadingList;
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

    @PostMapping("")
    public ResponseEntity<ReadingListResponseDto> createReadingList(@RequestBody ReadingListRequestDto readingListRequestDto) {
        ReadingList readingListToSave = readingListMapper.toEntity(readingListRequestDto);
        ReadingList savedReadingList = readingListService.createReadingList(readingListToSave);
        ReadingListResponseDto responseDto = readingListMapper.toResponseDto(savedReadingList);
        return ResponseEntity.created(null).body(responseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReadingListResponseDto>> getReadingListsByUserId(@PathVariable Long userId) {
        List<ReadingList> readingLists = readingListService.getReadingListByUserId(userId);
        List<ReadingListResponseDto> responseDtos = readingListMapper.toResponseDtoList(readingLists);
        return ResponseEntity.ok(responseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReadingListById(@PathVariable Long id) {
        readingListService.deleteReadingListById(id);
        return ResponseEntity.noContent().build();
    }
}
