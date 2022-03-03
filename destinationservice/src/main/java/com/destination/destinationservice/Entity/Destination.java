package com.destination.destinationservice.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

@Entity
@Getter
@Setter
@Table(name = "destinations")
public class Destination {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long destId;

    @Column(name = "place")
    private String place;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "info")
    private String info;

    @Column(name = "image")
    private String image;
}