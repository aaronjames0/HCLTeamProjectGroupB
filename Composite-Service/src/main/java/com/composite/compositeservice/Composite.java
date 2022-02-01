package com.composite.compositeservice;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "composite")
public class Composite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long compId;

    @Column(name = "destId")
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

    @Column(name = "reviews")
    private Review[] reviews;

    @Column(name = "recommendations")
    private Recommend[] recommendations;

    public long getDestId() {
        return destId;
    }

    public void setDestId(long destId) {
        this.destId = destId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /*
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Recommend> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommend> recommendations) {
        this.recommendations = recommendations;
    }
    */
}
