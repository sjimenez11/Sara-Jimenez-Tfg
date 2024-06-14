package com.connectedReads.repositories;

import com.connectedReads.entities.ReadingListBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingListBookRepository extends JpaRepository<ReadingListBook, Long> {
}
