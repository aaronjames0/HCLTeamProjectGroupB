package com.example.Recommendmicroservice.Controller;

import com.example.Recommendmicroservice.Entity.Recommend;
import com.example.Recommendmicroservice.MapStructDTO.RecommendDTO;
import com.example.Recommendmicroservice.Mapper.RecommendMapper;
import com.example.Recommendmicroservice.Service.RecommendService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    LoadBalancerClient loadBalancerClient;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RecommendDTO>>getRecommendById(@PathVariable long id)
    {
        return new ResponseEntity<>(recommendService.getRecommendById(id),HttpStatus.OK);
    }

    @RequestMapping("/destination/{destId}")
    public long[] getReviewIds(@PathVariable long destId) {
        List<RecommendDTO> recList = recommendService.getAllRecommend();
        for(int i = 0; i < recList.size(); i++) {
            if(recList.get(0).getDestId() == destId) continue;
            else recList.remove(i);
        }
        long[] recIds = new long[recList.size()];
        for(int i = 0; i < recList.size(); i++) {
            recIds[i] = recList.get(i).getRecommendationId();
        }
        return recIds;
    }

    @PostMapping("/post")
    public ResponseEntity<Recommend> addRecommend(@RequestBody RecommendDTO recommendDTO)
    {
        return new ResponseEntity<>(recommendService.addRecommend(recommendDTO),HttpStatus.CREATED);
    }

    private static final String MAIN_SERVICE = "mainService";

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = MAIN_SERVICE, fallbackMethod="testFallBack")
    public ResponseEntity<String> test(){
        String response = restTemplate.getForObject(getBaseUrl() + "/all", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);

    }

    private String getBaseUrl()
    {
        ServiceInstance serviceInstanceRecommend = loadBalancerClient.choose("destination-service");
        return serviceInstanceRecommend.getUri().toString();
    }

    private ResponseEntity<String> testFallBack(Exception e){
        return new ResponseEntity<String>("In fallback method", HttpStatus.INTERNAL_SERVER_ERROR);}
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RecommendDTO>> getAllRecommend()
    {
        return ResponseEntity.ok().body(recommendService.getAllRecommend());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Recommend> updateRecommend(@RequestBody RecommendDTO recommendDTO,@PathVariable("id") long recommendationId)
    {
        return ResponseEntity.ok().body(recommendService.updateRecommend(recommendDTO,recommendationId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecommend(@PathVariable("id") long recommendationId)
    {
        recommendService.deleteRecommendById(recommendationId);
        return ResponseEntity.ok().body("Recommend of ID " + recommendationId +" is deleted.");
    }
}