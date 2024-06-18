package com.traineeveronikadavydova.hotelWebsiteWithBooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "room_list")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "avg_rating")
    private Double avgRating;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
//    private Set<Reservation> reservationList;

}
