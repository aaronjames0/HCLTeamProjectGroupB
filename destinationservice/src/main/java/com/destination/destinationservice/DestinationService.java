package com.destination.destinationservice;

import java.util.List;

public interface DestinationService {
    Destination createDestination(Destination destination);

    Destination updateDestination(Destination destination);

    List<Destination> getAllDestinations();

    Destination[] getAllDestinationIds();

    Destination getDestinationById(long destId);
    
    void deleteDestination(long destId);
}