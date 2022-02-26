package com.example.Recommendmicroservice.Service;

import com.example.Recommendmicroservice.Entity.Recommend;
import com.example.Recommendmicroservice.MapStructDTO.RecommendDTO;

import java.util.List;
import java.util.Optional;

public interface RecommendService {
    Recommend addRecommend(RecommendDTO recommendDTO);
    Optional<RecommendDTO> getRecommendById(long recommendationId);
    Recommend updateRecommend(RecommendDTO recommendDTO,long recommendationId);
    String deleteRecommendById(long recommendationId);
    List<RecommendDTO> getAllRecommend();

}
