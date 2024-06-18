package com.connectedReads.services;

import com.connectedReads.dtos.ReviewRequestDto;
import com.connectedReads.entities.Book;
import com.connectedReads.entities.Review;
import com.connectedReads.entities.User;
import com.connectedReads.exceptions.ConnectedReadsException;
import com.connectedReads.exceptions.UserException;
import com.connectedReads.repositories.BookRepository;
import com.connectedReads.repositories.ReviewRepository;
import com.connectedReads.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public Review createReview(ReviewRequestDto requestDto){
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new ConnectedReadsException("Book with ID " + requestDto.getBookId() + " not found"));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new UserException("User with ID " + requestDto.getUserId() + " not found"));

        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setComment(requestDto.getComment());
        review.setRating(review.getRating());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, String comment, double rating){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ConnectedReadsException("Review with ID " + id + " not found"));
        review.setComment(comment);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByBookId(Long bookId){
        return reviewRepository.findReviewsByBookId(bookId);
    }

    public List<Review> getReviewsByUserId(Long userId){
        return reviewRepository.findReviewsByUserId(userId);
    }

    public void deleteReviewById(Long id){
        reviewRepository.deleteById(id);
    }
}

