package com.composite.compositeservice;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    private long[] reviews;

    @Column(name = "recommendations")
    private long[] recommendations;

    public long getDestId() {
        return 0;
    }

    public void setDestId(long destId) {
        this.destId = destId;
    }
}
