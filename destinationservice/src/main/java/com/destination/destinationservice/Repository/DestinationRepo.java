package com.destination.destinationservice.Repository;

import com.destination.destinationservice.Entity.Destination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepo extends JpaRepository<Destination, Long>{
    
}