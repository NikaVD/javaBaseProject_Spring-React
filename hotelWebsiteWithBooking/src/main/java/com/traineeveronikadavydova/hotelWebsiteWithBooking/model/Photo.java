package com.traineeveronikadavydova.hotelWebsiteWithBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "photo")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "photo_name", nullable = true)
    private String photosName;

    @Column(name = "type")
    private String type;

    @Lob
    private byte[] data;


    @ManyToOne(targetEntity = Room.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;


    @ManyToOne(targetEntity = Review.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;

    private String photosImagePath;

//    @Value("${location.path}")
//    private String locationPath;
//    private String uploadPath = "/storage/rooms_photos/";

//    @Transient
//    public String PhotosImagePath() {
//        return locationPath + room.getName() + "/" + photosName;
//    }



}
