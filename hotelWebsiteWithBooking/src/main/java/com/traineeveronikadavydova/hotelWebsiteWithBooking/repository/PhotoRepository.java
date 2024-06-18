package com.traineeveronikadavydova.hotelWebsiteWithBooking.repository;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Photo;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {



//    @Query(nativeQuery = true, value = "SELECT * FROM photo p WHERE room = ?1 AND review IS NULL")
    List<Photo> findByRoomAndReviewIsNull(Room room);

    List<Photo> findByReview(Review review);

    @Query(nativeQuery = true, value = "SELECT * FROM photo p WHERE p.review_id IS NULL")
    List<Photo> findAll();


}
