package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
