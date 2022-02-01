package com.destination.destinationservice;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    
    public long getDestId() {
        return destId;
    }
    public void setDestId(long destId) {
        this.destId = destId;
        return;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
        return;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
        return;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
        return;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
        return;
    }   
}