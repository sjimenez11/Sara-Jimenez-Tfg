package com.connectedReads.repositories;

import com.connectedReads.entities.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
    List<ReadingList> findReadingListsByUserId(Long userId);
}
