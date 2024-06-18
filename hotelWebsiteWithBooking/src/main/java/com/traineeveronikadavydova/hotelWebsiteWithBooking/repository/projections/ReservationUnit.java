package com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.projections;

import java.time.LocalDateTime;

public interface ReservationUnit {

    Long getUserId();
    String getName();
    Double getPrice();
    LocalDateTime getReservationTime();
}
