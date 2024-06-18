package com.connectedReads.controllers;

import com.connectedReads.dtos.ReviewRequestDto;
import com.connectedReads.dtos.ReviewResponseDto;
import com.connectedReads.entities.Review;
import com.connectedReads.mappers.ReviewMapper;
import com.connectedReads.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper reviewMapper;

    @PostMapping("")
    public ResponseEntity<ReviewResponseDto> createReview(
            @RequestBody ReviewRequestDto reviewRequestDto
            ){
        Review review = reviewService.createReview(reviewRequestDto);
        ReviewResponseDto responseDto = reviewMapper.toResponseDto(review);
        return ResponseEntity.created(null).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long id, String comment, double rating
    ){
        Review review = reviewService.updateReview(id, comment, rating);
        ReviewResponseDto reviewResponseDto = reviewMapper.toResponseDto(review);
        return ResponseEntity.ok(reviewResponseDto);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByBookId(
            @PathVariable Long bookId
    ){
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        List<ReviewResponseDto> responseDtos = reviewMapper.toResponseDtoList(reviews);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByUserId(
            @PathVariable Long userId
    ){
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        List<ReviewResponseDto> responseDtos = reviewMapper.toResponseDtoList(reviews);
        return ResponseEntity.ok(responseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long id){
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }
}
