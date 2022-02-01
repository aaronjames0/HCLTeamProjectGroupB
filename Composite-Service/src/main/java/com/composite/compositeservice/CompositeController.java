package com.composite.compositeservice;

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
        comp.setDestination(restTemplate.getForObject("http://localhost:8080/destinations/" + destId, Destination.class));
        long[] revIds = restTemplate.getForObject("http://localhost:8081/reviews/destination/" + destId, long[].class);
        for(int i = 0; i < revIds.length; i++) {
            comp.addReview(restTemplate.getForObject("http://localhost:8081/reviews/" + revIds[i], Review.class));
        }
        long[] recIds = restTemplate.getForObject("http://localhost:8082/recommend/destination/" + destId, long[].class);
        for(int i = 0; i < recIds.length; i++) {
            comp.addRec(restTemplate.getForObject("http://localhost:8082/recommend/" + recIds[i], Recommend.class));
        }
        return ResponseEntity.ok().body(comp);
    }
}
