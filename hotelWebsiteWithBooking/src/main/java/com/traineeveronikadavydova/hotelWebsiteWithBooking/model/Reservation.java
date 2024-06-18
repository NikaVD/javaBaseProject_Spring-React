package com.traineeveronikadavydova.hotelWebsiteWithBooking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
//@ValidDate
@Table(name = "reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Room.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;



    @Column(name = "reservation_time_From", nullable = false)
    private LocalDate reservationTimeFrom;

    @Column(name = "reservation_time_To", nullable = false)
    private LocalDate reservationTimeTo;

    @Column(name = "total_nights", nullable = false)
    @Min(1)
    @Max(value = 31, message = "it is not possible to book more than 31 days")
    private Long totalNights;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "reservation_number", nullable = false)
    private String reservationNumber;





}
