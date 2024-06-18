package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Photo;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

public interface PhotoService {
    Photo savePhoto(Photo photo);

    void uploadPhotoAdmin(MultipartFile[] multipartFiles, Room room) throws IOException;

    void uploadPhotoReview(MultipartFile[] multipartFiles, Room room, Review review) throws IOException;

    @PreDestroy
    void stop();

    List<Photo> findAllPhotosByReview(Review review);

    List<Photo> findAllPhotosByRoom(Room room);

    List<Photo> findAllPhotos();

//    Photo findPhotoById(Long photoId);

//    List<Photo> findPhotoForRoomPage(Long roomId);
}
