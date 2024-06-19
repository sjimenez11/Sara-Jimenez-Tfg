package com.connectedReads.services;

import com.connectedReads.dtos.BookForSaleRequestDto;
import com.connectedReads.entities.Book;
import com.connectedReads.entities.BookForSale;
import com.connectedReads.entities.User;
import com.connectedReads.entities.enums.SaleStatus;
import com.connectedReads.exceptions.ConnectedReadsException;
import com.connectedReads.exceptions.UserException;
import com.connectedReads.repositories.BookForSaleRepository;
import com.connectedReads.repositories.BookRepository;
import com.connectedReads.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class BookForSaleService {
    @Autowired
    private BookForSaleRepository bookForSaleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookForSale createBookForSale(BookForSaleRequestDto bfsRequestDto){
        Book book = bookRepository.findById(bfsRequestDto.getBookId())
                .orElseThrow(() -> new ConnectedReadsException("Book with ID " + bfsRequestDto.getBookId() + " not found"));

        User user = userRepository.findById(bfsRequestDto.getUserId())
                .orElseThrow(() -> new UserException("User with ID " + bfsRequestDto.getUserId() + " not found"));

        BookForSale bookForSale = new BookForSale();
        bookForSale.setBook(book);
        bookForSale.setUser(user);
        bookForSale.setStatus(bfsRequestDto.getStatus());
        bookForSale.setDescription(bfsRequestDto.getDescription());
        bookForSale.setPrice(bfsRequestDto.getPrice());
        return bookForSaleRepository.save(bookForSale);
    }

    public BookForSale updateBookForSaleByPrice(Long id, double newPrice){
        BookForSale book = bookForSaleRepository.findById(id)
                .orElseThrow(() -> new ConnectedReadsException("BookForSale with ID " + id + " not found"));

        book.setPrice(newPrice);
        return bookForSaleRepository.save(book);
    }

    public List<BookForSale> findBooksForSaleByUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with ID " + userId + " not found"));

        return bookForSaleRepository.findBookForSaleByUser(user);
    }

    public List<BookForSale> findBooksForSaleByTitle(String title){
        List<Book> books = bookRepository.findBooksByTitleContainingIgnoreCase(title);
        List<BookForSale> booksForSaleByTitle = new ArrayList<>();

        for (Book book : books) {
            List<BookForSale> booksForSale = bookForSaleRepository.findBookForSaleByBook(book);
            booksForSaleByTitle.addAll(booksForSale);
        }

        return booksForSaleByTitle;
    }

    public BookForSale updateBookForSaleByStatus(Long id, SaleStatus newStatus){
        BookForSale book = bookForSaleRepository.findById(id)
                .orElseThrow(() -> new ConnectedReadsException("BookForSale with ID " + id + " not found"));

        book.setStatus(newStatus);
        return bookForSaleRepository.save(book);
    }

    public void deleteBookForSaleById(Long id){
        bookForSaleRepository.deleteById(id);
    }
}
