package com.connectedReads.services;

import com.connectedReads.entities.ReadingListEntity;
import com.connectedReads.repositories.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;

    public ReadingListEntity createReadingList(ReadingListEntity readingList){
        return readingListRepository.save(readingList);
    }

    public void deleteReadingListById(Long id){
        readingListRepository.deleteById(id);
    }

    public List<ReadingListEntity> getReadingListByUserId(Long userId){
        return readingListRepository.findReadingListByUserIdContainsIgnoreCase(userId);
    }
}
