package com.connectedReads.repositories;

import com.connectedReads.entities.Book;
import com.connectedReads.entities.BookForSale;
import com.connectedReads.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookForSaleRepository extends JpaRepository<BookForSale, Long> {
    List<BookForSale> findBookForSaleByUser(User user);
    List<BookForSale> findBookForSaleByBook(Book book);
}
