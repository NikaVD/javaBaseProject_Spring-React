package com.traineeveronikadavydova.hotelWebsiteWithBooking.repository;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional <Room> findById (Long roomId);

    @Query(nativeQuery = true, value = "SELECT AVG(rating) " +
            "FROM review r WHERE r.room_Id = :roomId")
    Double findAvgRating(@Param("roomId") Long roomId);

    @Query(nativeQuery = true, value = "SELECT * FROM room_list rl " +
            "WHERE rl.id NOT IN (" +
            "SELECT r.room_id FROM reservation r " +
            "WHERE (r.reservation_time_from BETWEEN :dateFrom AND :dateTo) " +
            "AND (r.reservation_time_to BETWEEN :dateFrom AND :dateTo))")
    List<Room> findAvailableRooms(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);





}
