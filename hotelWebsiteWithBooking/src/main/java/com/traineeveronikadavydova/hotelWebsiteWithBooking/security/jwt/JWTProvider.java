package com.traineeveronikadavydova.hotelWebsiteWithBooking.security.jwt;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.security.UserPrinciple;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JWTProvider {

    String generateToken(UserPrinciple auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
