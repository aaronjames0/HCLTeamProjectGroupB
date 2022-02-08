package com.destination.destinationservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService{

    @Autowired
    private DestinationRepo destinationRepo;

    @Override
    public Destination createDestination(Destination destination) {
        return destinationRepo.save(destination);
    }

    @Override
    public Destination updateDestination(Destination destination) {
        Optional<Destination> destinationDb = this.destinationRepo.findById(destination.getDestId());
        if(destinationDb.isPresent()) {
            Destination destinationUpdate = destinationDb.get();
            destinationUpdate.setPlace(destination.getPlace());
            destinationUpdate.setCountry(destination.getCountry());
            destinationUpdate.setLatitude(destination.getLatitude());
            destinationUpdate.setLongitude(destination.getLongitude());
            destinationUpdate.setInfo(destination.getInfo());
            destinationUpdate.setImage(destination.getImage());
            destinationRepo.save(destinationUpdate);
            return destinationUpdate;
        }
        else {
            throw new DestinationExc("Record not found with id : " + destination.getDestId());
        }
    }

    @Override
    public List<Destination> getAllDestinations() {
        return this.destinationRepo.findAll();
    }

    @Override
    public Destination[] getAllDestinationIds() {
        List<Destination> dest = getAllDestinations();
        Destination[] dests = new Destination[dest.size()];
        for(int i = 0; i < dest.size(); i++) {
            dests[i] = dest.get(i);
        }
        return dests;
    }

    @Override
    public Destination getDestinationById(long destinationId) {
        Optional<Destination> destinationDb = this.destinationRepo.findById(destinationId);
        if(destinationDb.isPresent()) {
            return destinationDb.get();
        }
        else {
            throw new DestinationExc("Record not found with id : " + destinationId);
        }
    }

    @Override
    public void deleteDestination(long destinationId) {
        Optional<Destination> destinationDb = this.destinationRepo.findById(destinationId);
        if(destinationDb.isPresent()) {
            this.destinationRepo.delete(destinationDb.get());
        }
        else {
            throw new DestinationExc("Record not found with id : " + destinationId);
        }
    }
    
}
