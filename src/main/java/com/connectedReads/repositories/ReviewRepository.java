package com.connectedReads.repositories;

import com.connectedReads.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByBookId(Long bookId);
    List<Review> findReviewsByUserId(Long userId);
}
