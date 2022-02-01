package com.composite.compositeservice;

public class Destination {
    private long destId;
    private String place;
    private String country;
    private double latitude;
    private double longitude;
    private String info;
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
