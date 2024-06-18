package com.connectedReads.services;

import com.connectedReads.entities.Book;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.ReadingListBook;
import com.connectedReads.exceptions.ConnectedReadsException;
import com.connectedReads.repositories.BookRepository;
import com.connectedReads.repositories.ReadingListBookRepository;
import com.connectedReads.repositories.ReadingListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Component
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;

    public ReadingList createReadingList(ReadingList readingList) {
        return readingListRepository.save(readingList);
    }

    public void deleteReadingListById(Long id){
        readingListRepository.deleteById(id);
    }

    public List<ReadingList> getReadingListByUserId(Long userId){
        return readingListRepository.findReadingListsByUserId(userId);
    }
}
