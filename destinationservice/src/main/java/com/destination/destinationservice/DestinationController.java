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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/place/{id}")
    public String getDestPlace(@PathVariable long id) {
        return destinationService.getDestinationById(id).getPlace();
    }

    @GetMapping("/country/{id}")
    public String getDestCountry(@PathVariable long id) {
        return destinationService.getDestinationById(id).getCountry();
    }

    @GetMapping("/latitude/{id}")
    public Double getDestLatitude(@PathVariable long id) {
        return destinationService.getDestinationById(id).getLatitude();
    }

    @GetMapping("/longitude/{id}")
    public Double getDestLongitude(@PathVariable long id) {
        return destinationService.getDestinationById(id).getLongitude();
    }

    @GetMapping("/info/{id}")
    public String getDestInfo(@PathVariable long id) {
        return destinationService.getDestinationById(id).getInfo();
    }

    @GetMapping("/image/{id}")
    public String getDestImage(@PathVariable long id) {
        return destinationService.getDestinationById(id).getImage();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Destination>> getAllDestination() {
        return ResponseEntity.ok().body(destinationService.getAllDestinations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable long id) {
        return ResponseEntity.ok().body(destinationService.getDestinationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        return ResponseEntity.ok().body(this.destinationService.createDestination(destination));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable long id, @RequestBody Destination destination) {
        destination.setDestId(id);
        return ResponseEntity.ok().body(this.destinationService.updateDestination(destination));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteDestination(@PathVariable long id) {
        this.destinationService.deleteDestination(id);
        return HttpStatus.OK;
    }
}
