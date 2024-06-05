package com.connectedReads.services;

import com.connectedReads.entities.BookEntity;
import com.connectedReads.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookEntity createBook(BookEntity book){
        return bookRepository.save(book);
    }

    public BookEntity getBookById(Long id){
        Optional<BookEntity> optionalBook = bookRepository.findById(id);
        return optionalBook.get();
    }

    public List<BookEntity> getAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
