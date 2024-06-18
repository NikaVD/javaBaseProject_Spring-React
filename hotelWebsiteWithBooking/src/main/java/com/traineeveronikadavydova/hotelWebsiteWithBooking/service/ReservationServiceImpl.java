package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Reservation;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.ReservationRepository;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.projections.ReservationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation saveReservation(Reservation reservation, User user) {
        reservation.setTotalNights(ChronoUnit.DAYS.between(reservation.getReservationTimeFrom(), reservation.getReservationTimeTo()));
        reservation.setUser(user);
        reservation.setReservationNumber(user.getUsername() + UUID.randomUUID());
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteReservation(id);
    }


    @Override
    public List<Reservation> findReservationOfUser(User user) {
        return reservationRepository.findByUser(user);

    }
}
