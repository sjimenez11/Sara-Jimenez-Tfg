package com.connectedReads.services;

import com.connectedReads.entities.Book;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.ReadingListBook;
import com.connectedReads.exceptions.connectedReadsException;
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

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReadingListBookRepository readingListBookRepository;

    public ReadingList createReadingList(ReadingList readingList) {
        return readingListRepository.save(readingList);
    }

    //TODO hace falta?? primero sería crear la lista y luego añadir los libros???
    @Transactional
    public ReadingList createReadingList(ReadingList readingList, Set<Long> bookIds) {
        readingList = readingListRepository.save(readingList); //guardamos lista para obtener su id

        for (Long bookId : bookIds) {
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                ReadingListBook readingListBook = new ReadingListBook();
                readingListBook.setBook(book);
                readingListBook.setReadingList(readingList);
                readingListBookRepository.save(readingListBook);
            } else {
                throw new connectedReadsException("Book with ID " + bookId + " not found");
            }
        }

        return readingList;
    }

    public void deleteReadingListById(Long id){
        readingListRepository.deleteById(id);
    }

    public List<ReadingList> getReadingListByUserId(Long userId){
        return readingListRepository.findReadingListsByUserId(userId);
    }
}
