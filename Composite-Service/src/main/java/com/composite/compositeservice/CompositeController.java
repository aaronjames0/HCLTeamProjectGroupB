package com.composite.compositeservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/composite")
public class CompositeController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/destination/{destId}")
    public ResponseEntity<Composite> createComposite(@PathVariable long destId) {
        Composite comp = new Composite();
        comp.destination = restTemplate.getForObject("http://localhost:8080/destinations/" + destId, Destination.class);
        long[] revIds = restTemplate.getForObject("http://localhost:8081/reviews/destination/" + destId, long[].class);
        for(int i = 0; i < revIds.length; i++) {
            comp.reviews.add(restTemplate.getForObject("http://localhost:8081/reviews/" + revIds[i], Review.class));
        }
        long[] recIds = restTemplate.getForObject("http://localhost:8082/recommend/destination/" + destId, long[].class);
        for(int i = 0; i < recIds.length; i++) {
            comp.recommendations.add(restTemplate.getForObject("http://localhost:8082/recommend/" + recIds[i], Recommend.class));
        }
        return ResponseEntity.ok().body(comp);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Composite>> getUserComposite(@PathVariable long userId) {
        List<Composite> comps = new ArrayList<Composite>();
        Destination[] dests = restTemplate.getForObject("http://localhost:8080/destinations/all", Destination[].class);
        for(int i = 0; i < dests.length; i++) {
            Composite comp = new Composite();
            comp.destination = restTemplate.getForObject("http://localhost:8080/destinations/" + dests[i].getDestId(), Destination.class);
            long[] revIds = restTemplate.getForObject("http://localhost:8081/reviews/destination/" + dests[i].getDestId(), long[].class);
            for(int j = 0; j < revIds.length; j++) {
                comp.reviews.add(restTemplate.getForObject("http://localhost:8081/reviews/" + revIds[j], Review.class));
            }
            long[] recIds = restTemplate.getForObject("http://localhost:8082/recommend/destination/" + dests[i].getDestId(), long[].class);
            for(int j = 0; j < recIds.length; j++) {
                comp.recommendations.add(restTemplate.getForObject("http://localhost:8082/recommend/" + recIds[j], Recommend.class));
            }
            comps.add(comp);
        }
        return ResponseEntity.ok().body(comps);
    }
}