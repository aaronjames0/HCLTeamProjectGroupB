package com.composite.compositeservice;

import java.util.ArrayList;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/composite")
public class CompositeController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/destination/{destId}")
    @CircuitBreaker(name = "getdestination",fallbackMethod = "fall")
    public ResponseEntity<Composite> createComposite(@PathVariable long destId) {
        Composite comp = new Composite();
        comp.destination = restTemplate.getForObject(getDestUrl() + "/destinations/" + destId, Destination.class);
        long[] revIds = restTemplate.getForObject(getRevUrl() + "/reviews/destination/" + destId, long[].class);
        for(int i = 0; i < revIds.length; i++) {
            comp.reviews.add(restTemplate.getForObject(getRevUrl() + "/reviews/" + revIds[i], Review.class));
        }
        long[] recIds = restTemplate.getForObject(getRecUrl() + "/recommend/destination/" + destId, long[].class);
        for(int i = 0; i < recIds.length; i++) {
            comp.recommendations.add(restTemplate.getForObject(getRecUrl()  + "/recommend/" + recIds[i], Recommend.class));
        }
        return ResponseEntity.ok().body(comp);
    }

    @GetMapping("/user/{userId}")
    @CircuitBreaker(name="getuser",fallbackMethod = "fall")
    public ResponseEntity<List<Composite>> getUserComposite(@PathVariable long userId) {
        List<Composite> comps = new ArrayList<Composite>();
        Destination[] dests = restTemplate.getForObject(getDestUrl() + "/destinations/all", Destination[].class);
        for(int i = 0; i < dests.length; i++) {
            Composite comp = new Composite();
            comp.destination = restTemplate.getForObject(getDestUrl() + "/destinations/" + dests[i].getDestId(), Destination.class);
            long[] revIds = restTemplate.getForObject(getRevUrl() + "/reviews/destination/" + dests[i].getDestId(), long[].class);
            for(int j = 0; j < revIds.length; j++) {
                comp.reviews.add(restTemplate.getForObject(getRevUrl() + "/reviews/" + revIds[j], Review.class));
            }
            long[] recIds = restTemplate.getForObject(getRecUrl() + "/recommend/destination/" + dests[i].getDestId(), long[].class);
            for(int j = 0; j < recIds.length; j++) {
                comp.recommendations.add(restTemplate.getForObject(getRecUrl() + "/recommend/" + recIds[j], Recommend.class));
            }
            comps.add(comp);
        }
        return ResponseEntity.ok().body(comps);
    }

    public String getDestUrl() {
        return System.getenv("DESTINATION_URL");
    }

    public String getRecUrl() {
        return System.getenv("RECOMMEND_URL");
    }

    public String getRevUrl() {
        return System.getenv("REVIEW_URL");
    }

    private ResponseEntity<String> fall(Exception e){
        return new ResponseEntity<String>("In fallback method", HttpStatus.INTERNAL_SERVER_ERROR);}
}