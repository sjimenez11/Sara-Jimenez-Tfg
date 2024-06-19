package com.connectedReads.services;

import com.connectedReads.dtos.ReadingListRequestDto;
import com.connectedReads.entities.Book;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.ReadingListBook;
import com.connectedReads.entities.User;
import com.connectedReads.exceptions.ConnectedReadsException;
import com.connectedReads.exceptions.UserException;
import com.connectedReads.repositories.BookRepository;
import com.connectedReads.repositories.ReadingListBookRepository;
import com.connectedReads.repositories.ReadingListRepository;
import com.connectedReads.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReadingListBookRepository readingListBookRepository;

    public ReadingList createReadingList(ReadingListRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new UserException("User with ID " + requestDto.getUserId() + " not found"));


        Set<ReadingListBook> readingListBooks = new HashSet<>();
        if(requestDto.getBookIds() != null) {
            Set<Long> readingListBookIds = requestDto.getBookIds();
            for (Long id : readingListBookIds) {
                ReadingListBook readingListBook = readingListBookRepository.findById(id)
                        .orElseThrow(() -> new ConnectedReadsException("ReadingListBook with ID " + id + " not found"));
                readingListBooks.add(readingListBook);
            }
        }

        ReadingList readingList = new ReadingList();
        readingList.setUser(user);
        readingList.setName(requestDto.getName());
        readingList.setDescription(requestDto.getDescription());
        readingList.setReadingListBooks(readingListBooks);
        return readingListRepository.save(readingList);
    }

    public void deleteReadingListById(Long id){
        readingListRepository.deleteById(id);
    }

    public List<ReadingList> getReadingListByUserId(Long userId){
        return readingListRepository.findReadingListsByUserId(userId);
    }
}
