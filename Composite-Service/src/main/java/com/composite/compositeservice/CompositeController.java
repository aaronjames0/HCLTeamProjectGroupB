package com.composite.compositeservice;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CompositeController {

    private RestTemplate restTemplate = new RestTemplate();
    //private List<Review> reviews = new ArrayList<Review>();
    

    @GetMapping("/destinations/comp/{id}")
    public ResponseEntity<Composite> createComposite(@PathVariable long id) {
        Composite comp = new Composite();
        comp.setDestId(id);
        comp.setPlace(restTemplate.getForObject("http://localhost:8080/destinations/place/" + String.valueOf(id), String.class));
        comp.setCountry(restTemplate.getForObject("http://localhost:8080/destinations/country/" + String.valueOf(id), String.class));
        comp.setLatitude(restTemplate.getForObject("http://localhost:8080/destinations/latitude/" + String.valueOf(id), Double.class));
        comp.setLongitude(restTemplate.getForObject("http://localhost:8080/destinations/longitude/" + String.valueOf(id), Double.class));
        comp.setInfo(restTemplate.getForObject("http://localhost:8080/destinations/info/" + String.valueOf(id), String.class));
        comp.setImage(restTemplate.getForObject("http://localhost:8080/destinations/image/" + String.valueOf(id), String.class));
        comp.setTemp(restTemplate.getForObject("http://localhost:8081/reviews/destination/" + String.valueOf(id), long[].class));
        return ResponseEntity.ok().body(comp);
    }
}
