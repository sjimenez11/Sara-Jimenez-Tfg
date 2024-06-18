package com.connectedReads.mappers;

import com.connectedReads.dtos.BookResponseDto;
import com.connectedReads.dtos.ReviewRequestDto;
import com.connectedReads.dtos.ReviewResponseDto;
import com.connectedReads.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {
    private final BookMapper bookMapper;

    @Autowired
    public ReviewMapper(BookMapper bookMapper){
        this.bookMapper = bookMapper;
    }

    public Review toEntity(ReviewRequestDto requestDto){
        Review review = new Review();
        //no contemplo setBookId ni setUserId porque en una reseña no se puede cambiar esos valores
        review.setComment(review.getComment());
        review.setRating(review.getRating());

        return review;
    }

    public ReviewResponseDto toResponseDto(Review review){
        BookResponseDto book = bookMapper.toResponseDto(review.getBook());

        return new ReviewResponseDto(
                review.getId(),
                review.getUser().getId(),
                book,
                review.getComment(),
                review.getRating()
        );
    }

    public List<ReviewResponseDto> toResponseDtoList(List<Review> reviews){
        return reviews.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
