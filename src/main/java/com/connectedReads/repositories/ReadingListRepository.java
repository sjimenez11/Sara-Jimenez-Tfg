package com.connectedReads.repositories;

import com.connectedReads.entities.ReadingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingListEntity, Long> {
    List<ReadingListEntity> findReadingListByUserIdContainsIgnoreCase(Long userId);
}
