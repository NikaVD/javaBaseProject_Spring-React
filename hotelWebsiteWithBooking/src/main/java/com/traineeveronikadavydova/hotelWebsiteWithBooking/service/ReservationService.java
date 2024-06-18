package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Reservation;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.projections.ReservationUnit;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation saveReservation(Reservation reservation, User user);

    void deleteReservation(Long id);

    List<Reservation> findReservationOfUser(User user);
}
