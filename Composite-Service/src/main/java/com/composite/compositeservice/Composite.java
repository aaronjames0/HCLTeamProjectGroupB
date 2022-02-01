package com.composite.compositeservice;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    public Destination destination;
    public List<Review> reviews  = new ArrayList<Review>();
    public List<Recommend> recommendations = new ArrayList<Recommend>();
}