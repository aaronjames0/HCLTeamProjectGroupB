package com.example.Recommendmicroservice.Service;

import com.example.Recommendmicroservice.Entity.Recommend;
import com.example.Recommendmicroservice.MapStructDTO.RecommendDTO;
import com.example.Recommendmicroservice.Mapper.RecommendMapper;
import com.example.Recommendmicroservice.Repository.RecommendRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class RecommendServiceImp implements RecommendService {

    @Autowired
    public final RecommendRepo recommendRepo;

    @Autowired
    private RecommendMapper recommendMapper;

//    public RecommendServiceImp(RecommendRepo recommendRepo) {
//        this.recommendRepo = recommendRepo;
//    }

    @Override
    public Recommend addRecommend(RecommendDTO recommendDTO)
    {
        return recommendRepo.save(recommendMapper.DtoToEntity(recommendDTO));
    }

    @Override
    public Optional<RecommendDTO> getRecommendById(long recommendationId)
    {
        return Optional.ofNullable(recommendMapper.entityTODto(recommendRepo.findById(recommendationId).get()));
    }

    @Override
    public Recommend updateRecommend(RecommendDTO recommend,long recommendationId)
    {
        RecommendDTO recommendDB = new RecommendDTO();
        recommendDB.setRecommendationId(recommend.getRecommendationId());
        recommendDB.setAuthor(recommend.getAuthor());
        recommendDB.setRate(recommend.getRate());
        recommendDB.setDestId(recommend.getDestId());
        recommendDB.setContent(recommend.getContent());
        return recommendRepo.save(recommendMapper.DtoToEntity(recommendDB));
    }

    @Override
    public String deleteRecommendById(long recommendationId)
    {
        RecommendDTO recommendDTO = recommendMapper.entityTODto(recommendRepo.findById(recommendationId).get());
        recommendRepo.deleteById(recommendDTO.getRecommendationId());
        return recommendationId+"deleted";
    }

    @Override
    public List<RecommendDTO> getAllRecommend()
    {
        return recommendMapper.entitiesToDto(this.recommendRepo.findAll());
    }



}
