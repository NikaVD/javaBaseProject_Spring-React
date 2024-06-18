package com.traineeveronikadavydova.hotelWebsiteWithBooking.controller;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Role;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.security.UserPrinciple;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.PhotoService;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.ReviewService;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.RoomService;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@RequestBody User user, @PathVariable Role role) {
        String username = user.getUsername();
        userService.changeRole(role, username);
        return ResponseEntity.ok(true);
    }

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }


    @PostMapping(value = "{roomId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhotosAdmin(@PathVariable Long roomId, @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        Room room = roomService.getRoomById(roomId);
        photoService.uploadPhotoAdmin(multipartFiles, room);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("room")
    public ResponseEntity<?> saveRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.CREATED);
    }


    @DeleteMapping("room/{roomId}")
    public ResponseEntity<?> deleteRoom (@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<?> deleteUser (@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
