package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room saveRoom(Room room);

    void deleteRoom(Long id);


    Room getRoomById(Long id);

    void updateRoomRating(Room room);

    List<Room> findAllRoom();

    List<Room> findAllAvailableRooms(LocalDate dateFrom, LocalDate dateTo);
}
