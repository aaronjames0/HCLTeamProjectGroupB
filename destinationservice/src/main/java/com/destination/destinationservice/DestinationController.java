package com.destination.destinationservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {
    
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/destinations/count")
    public int getCount() {
        return 5;
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<Destination>> getAllDestination() {
        return ResponseEntity.ok().body(destinationService.getAllDestinations());
    }

    @GetMapping("/destinations/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable long id) {
        return ResponseEntity.ok().body(destinationService.getDestinationById(id));
    }

    @PostMapping("/destinations")
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        return ResponseEntity.ok().body(this.destinationService.createDestination(destination));
    }

    @PutMapping("/destinations/update/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable long id, @RequestBody Destination destination) {
        destination.setDestId(id);
        return ResponseEntity.ok().body(this.destinationService.updateDestination(destination));
    }

    @DeleteMapping("/destinations/{id}")
    public HttpStatus deleteDestination(@PathVariable long id) {
        this.destinationService.deleteDestination(id);
        return HttpStatus.OK;
    }
}
