package com.traineeveronikadavydova.hotelWebsiteWithBooking.repository;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Reservation;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.projections.ReservationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);
    Optional <Reservation> findById (Long reservationId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM reservation WHERE id = :reservationId")
    void deleteReservation(@Param("reservationId") Long reservationId);




}
