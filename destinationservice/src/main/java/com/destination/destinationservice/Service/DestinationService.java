package com.destination.destinationservice.Service;

import java.util.List;

import com.destination.destinationservice.Entity.Destination;

public interface DestinationService {
    Destination createDestination(Destination destination);

    Destination updateDestination(Destination destination);

    List<Destination> getAllDestinations();

    Destination[] getAllDestinationIds();

    Destination getDestinationById(long destId);
    
    void deleteDestination(long destId);
}