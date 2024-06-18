package com.traineeveronikadavydova.hotelWebsiteWithBooking.controller;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.*;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.security.UserPrinciple;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReservationService reservationService;


    @PostMapping(value = "photo/{roomId}/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhotosReview(@PathVariable Long roomId, @PathVariable Long reviewId, @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        Room room = roomService.getRoomById(roomId);
        Review review = reviewService.getReviewById(reviewId);
        photoService.uploadPhotoReview(multipartFiles, room, review);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("reservation")
//
    public ResponseEntity<?> saveReservation(@Valid @RequestBody Reservation reservation, @AuthenticationPrincipal UserPrinciple userPrinciple) {
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        LocalDate dateFrom = reservation.getReservationTimeFrom();
        LocalDate dateTo = reservation.getReservationTimeTo();
        if (!roomService.findAllAvailableRooms(dateFrom, dateTo).contains(reservation.getRoom())) {
            return new ResponseEntity<>("Room is not available", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservationService.saveReservation(reservation, user), HttpStatus.CREATED);

    }

    @GetMapping("reservation")
    public ResponseEntity<?> getAllReservation(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        return ResponseEntity.ok(reservationService.findReservationOfUser(user));

    }

    @PostMapping("review")
    public ResponseEntity<?> saveReview(@RequestBody Review review, @AuthenticationPrincipal UserPrinciple userPrinciple) {
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        reviewService.saveReview(review, user);
        Long reviewId = review.getId();
        Room room = review.getRoom();
        roomService.updateRoomRating(room);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);

    }

    @DeleteMapping("reservation/{reservationId}")
    public ResponseEntity<?> deleteReservation (@PathVariable("reservationId") Long reservationId) {
        reservationService.deleteReservation(reservationId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
