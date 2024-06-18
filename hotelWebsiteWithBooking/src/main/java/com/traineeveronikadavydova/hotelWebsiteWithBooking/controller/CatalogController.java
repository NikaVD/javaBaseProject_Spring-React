package com.traineeveronikadavydova.hotelWebsiteWithBooking.controller;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.PhotoService;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.ReviewService;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/public")
public class CatalogController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("photo/review/{reviewId}")
    public ResponseEntity<?> GetAllPhotosForReview (@PathVariable("reviewId") Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        return new ResponseEntity<>(photoService.findAllPhotosByReview(review), HttpStatus.OK);
    }

    @GetMapping("photo/{roomId}")
    public ResponseEntity<?> GetAllPhotosForRoom (@PathVariable("roomId") Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(photoService.findAllPhotosByRoom(room), HttpStatus.OK);
    }

    @GetMapping("photo")
    public ResponseEntity<?> GetAllPhotos () {
        return new ResponseEntity<>(photoService.findAllPhotos(), HttpStatus.OK);
    }

    @GetMapping("photo/room/{roomId}")
    public ResponseEntity<?> GetPhotoForRoomPage (@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(photoService.findAllPhotosByRoom(room), HttpStatus.OK);
    }

    @GetMapping("review/{roomId}")
    public ResponseEntity<?> getAllReview(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(reviewService.findReviewOfRoom(room), HttpStatus.OK);
    }

    @GetMapping("room/{roomId}")
    public ResponseEntity<?>getRoomById(@PathVariable Long roomId) {
        return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @GetMapping("room")
    public ResponseEntity<?> getAllRooms() {
        return new ResponseEntity<>(roomService.findAllRoom(), HttpStatus.OK);
    }

    @GetMapping("room/dates")
    public ResponseEntity<?> getAllAvailableRooms(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dateFrom, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dateTo) {
        return new ResponseEntity<>(roomService.findAllAvailableRooms(dateFrom, dateTo), HttpStatus.OK);
    }
}
