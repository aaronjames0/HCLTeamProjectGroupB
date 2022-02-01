package com.composite.compositeservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CompositeController {

    private RestTemplate restTemplate = new RestTemplate();
    

    @GetMapping("/destinations/comp/all/{id}")
    public ResponseEntity<Composite> createComposite(@PathVariable long id) {
        Composite comp = new Composite();
        comp.setDestination(restTemplate.getForObject("http://localhost:8080/destinations/" + id, Destination.class));
        long[] revIds = restTemplate.getForObject("http://localhost:8081/reviews/destination/" + id, long[].class);
        for(int i = 0; i < revIds.length; i++) {
            comp.addReview(restTemplate.getForObject("http://localhost:8081/reviews/" + revIds[i], Review.class));
        }
        long[] recIds = restTemplate.getForObject("http://localhost:8082/recommend/destination/" + id, long[].class);
        for(int i = 0; i < recIds.length; i++) {
            comp.addRec(restTemplate.getForObject("http://localhost:8082/recommend/" + recIds[i], Recommend.class));
        }
        return ResponseEntity.ok().body(comp);
    }
}
