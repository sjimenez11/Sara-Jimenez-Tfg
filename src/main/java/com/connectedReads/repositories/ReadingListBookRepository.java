package com.connectedReads.repositories;

import com.connectedReads.entities.ReadingListBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingListBookRepository extends JpaRepository<ReadingListBookEntity, Long> {
}
