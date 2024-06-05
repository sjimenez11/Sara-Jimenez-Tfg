package com.connectedReads.repositories;

import com.connectedReads.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findBooksByTitleContainsIgnoreCase(String title);

}
