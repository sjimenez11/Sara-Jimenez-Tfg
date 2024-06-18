package com.connectedReads.services;

import com.connectedReads.dtos.ReadingListBookRequestDto;
import com.connectedReads.dtos.ReadingListRequestDto;
import com.connectedReads.entities.Book;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.ReadingListBook;
import com.connectedReads.entities.enums.ReadingStatus;
import com.connectedReads.exceptions.ConnectedReadsException;
import com.connectedReads.repositories.BookRepository;
import com.connectedReads.repositories.ReadingListBookRepository;
import com.connectedReads.repositories.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ReadingListBookService {
    @Autowired
    private ReadingListBookRepository readingListBookRepository;

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private BookRepository bookRepository;

    public ReadingListBook addReadingListBook(ReadingListBookRequestDto rlbRequestDto){
        ReadingList readingList = readingListRepository.findById(rlbRequestDto.getIdReadingList())
                .orElseThrow(() -> new ConnectedReadsException("ReadingList with ID" + rlbRequestDto.getIdReadingList() + " not found"));

        Book book = bookRepository.findById(rlbRequestDto.getIdBook())
                .orElseThrow(() -> new ConnectedReadsException("Book with ID " + rlbRequestDto.getIdBook() + " not found"));

        ReadingListBook readingListBook = new ReadingListBook();
        readingListBook.setBook(book);
        readingListBook.setReadingList(readingList);
        readingListBook.setStatus(rlbRequestDto.getStatus());
        return readingListBookRepository.save(readingListBook);
    }

    public ReadingListBook updateReadingListBookStatus(Long id, ReadingStatus status){
        ReadingListBook readingListBook = readingListBookRepository.findById(id)
                        .orElseThrow(() -> new ConnectedReadsException("ReadingListBook with ID " +  id + "not found"));
        readingListBook.setStatus(status);
        return readingListBook;
    }

    public void deleteReadingListBookById(Long id){
        readingListBookRepository.deleteById(id);
    }
}
