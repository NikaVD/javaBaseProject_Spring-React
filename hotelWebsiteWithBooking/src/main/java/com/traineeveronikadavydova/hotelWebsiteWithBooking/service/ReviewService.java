package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;

import java.util.List;

public interface ReviewService {

    Review getReviewById(Long id);

    Review saveReview(Review review, User user);

    List<Review> findReviewOfRoom(Room room);
}
