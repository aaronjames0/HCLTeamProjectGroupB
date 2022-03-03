package com.composite.compositeservice;

import java.util.ArrayList;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
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

    //CompositeMapper mapper = Mappers.getMapper(CompositeMapper.class);

    @GetMapping("/destination/{destId}")
    @CircuitBreaker(name = "getdestination",fallbackMethod = "fall")
    public ResponseEntity<Composite> createComposite(@PathVariable long destId) {
        Destination dest = (restTemplate.getForObject(getDestUrl() + "/destinations/" + destId, Destination.class));
        if(dest.getDestId() == 0) {
            dest.setPlace("No destination found with id " + destId);
        }
        long[] revIds = restTemplate.getForObject(getRevUrl() + "/reviews/destination/" + destId, long[].class);
        List<Review> reviews = new ArrayList<Review>();
        for(int i = 0; i < revIds.length; i++) {
            reviews.add(restTemplate.getForObject(getRevUrl() + "/reviews/" + revIds[i], Review.class));
        }
        long[] recIds = restTemplate.getForObject(getRecUrl() + "/recommend/destination/" + destId, long[].class);
        List<Recommend> recommends = new ArrayList<Recommend>();
        for(int i = 0; i < recIds.length; i++) {
            recommends.add(restTemplate.getForObject(getRecUrl()  + "/recommend/" + recIds[i], Recommend.class));
        }
        Composite comp = new Composite();
        comp.setDestination(dest);
        comp.setRecommendations(recommends);
        comp.setReviews(reviews);
        return ResponseEntity.ok().body(comp);
    }

    @GetMapping("/user/{userId}")
    @CircuitBreaker(name="getuser",fallbackMethod = "fall")
    public ResponseEntity<List<Composite>> getUserComposite(@PathVariable long userId) {
        List<Composite> comps = new ArrayList<Composite>();
        Destination[] dests = restTemplate.getForObject(getDestUrl() + "/destinations/all", Destination[].class);
        for(int i = 0; i < dests.length; i++) {
            long[] revIds = restTemplate.getForObject(getRevUrl() + "/reviews/destination/" + dests[i].getDestId(), long[].class);
            List<Review> reviews = new ArrayList<Review>();
            for(int j = 0; j < revIds.length; j++) {
                reviews.add(restTemplate.getForObject(getRevUrl() + "/reviews/" + revIds[j], Review.class));
            }
            long[] recIds = restTemplate.getForObject(getRecUrl() + "/recommend/destination/" + dests[i].getDestId(), long[].class);
            List<Recommend> recommends = new ArrayList<Recommend>();
            for(int j = 0; j < recIds.length; j++) {
                recommends.add(restTemplate.getForObject(getRecUrl() + "/recommend/" + recIds[j], Recommend.class));
            }
            Composite comp = new Composite();
            comp.setDestination(dests[i]);
            comp.setRecommendations(recommends);
            comp.setReviews(reviews);
            comps.add(comp);
        }
        return ResponseEntity.ok().body(comps);
    }

    public String getDestUrl() {
        //return "http://localhost:8080"; //for local testing
        return System.getenv("DESTINATION_URL");    //for live
    }

    public String getRecUrl() {
        //return "http://localhost:8082";   //for local testing
        return System.getenv("RECOMMEND_URL");  //for live
    }

    public String getRevUrl() {
        //return "http://localhost:8081";   //for local testing
        return System.getenv("REVIEW_URL");   //for live
    }

    private ResponseEntity<String> fall(Exception e){
        return new ResponseEntity<String>("In fallback method", HttpStatus.INTERNAL_SERVER_ERROR);}
}