package com.connectedReads.services;

import com.connectedReads.dtos.BookRequestDto;
import com.connectedReads.entities.Book;
import com.connectedReads.entities.Review;
import com.connectedReads.repositories.BookRepository;
import com.connectedReads.repositories.ReviewRepository;
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
    @Autowired
    private ReviewRepository reviewRepository;

    public Book createBook(BookRequestDto requestDto){
        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setSynopsis(requestDto.getSynopsis());
        return bookRepository.save(book);
    }

    public Book getBookById(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.get();
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> findBooksByTitle(String title){
        return bookRepository.findBooksByTitleContainingIgnoreCase(title);
    }
}
