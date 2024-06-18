package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room getRoomById(Long id) { return roomRepository.findById(id).get();}

    @Override
    public void updateRoomRating(Room room) {
        Optional<Room> optionalRoom = roomRepository.findById(room.getId());
        Double avgRating = roomRepository.findAvgRating(room.getId());
        optionalRoom.get().setAvgRating(avgRating);
        saveRoom(optionalRoom.get());
    }


    @Override
    public List<Room> findAllRoom(){
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findAllAvailableRooms(LocalDate dateFrom, LocalDate dateTo) {
        return roomRepository.findAvailableRooms(dateFrom, dateTo);
    }
}
