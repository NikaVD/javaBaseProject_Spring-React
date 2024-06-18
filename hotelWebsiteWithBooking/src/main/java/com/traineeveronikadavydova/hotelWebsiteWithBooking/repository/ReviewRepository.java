package com.traineeveronikadavydova.hotelWebsiteWithBooking.repository;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Reservation;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRoom(Room room);
    Optional<Review> findById (Long reviewId);

}
