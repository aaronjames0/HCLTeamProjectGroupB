package com.composite.compositeservice;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Composite {
    private Destination destination;
    private List<Review> reviews  = new ArrayList<Review>();
    private List<Recommend> recommendations = new ArrayList<Recommend>();
}