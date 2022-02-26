package com.example.Recommendmicroservice.Mapper;

import com.example.Recommendmicroservice.Entity.Recommend;
import com.example.Recommendmicroservice.MapStructDTO.RecommendDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-25T14:25:58-0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class RecommendMapperImpl implements RecommendMapper {

    @Override
    public RecommendDTO entityTODto(Recommend recommend) {
        if ( recommend == null ) {
            return null;
        }

        RecommendDTO recommendDTO = new RecommendDTO();

        recommendDTO.setDestId( recommend.getDestId() );
        recommendDTO.setRecommendationId( recommend.getRecommendationId() );
        recommendDTO.setAuthor( recommend.getAuthor() );
        recommendDTO.setContent( recommend.getContent() );
        recommendDTO.setRate( recommend.getRate() );

        return recommendDTO;
    }

    @Override
    public Recommend DtoToEntity(RecommendDTO recommendDTO) {
        if ( recommendDTO == null ) {
            return null;
        }

        Recommend recommend = new Recommend();

        recommend.setDestId( recommendDTO.getDestId() );
        recommend.setRecommendationId( recommendDTO.getRecommendationId() );
        recommend.setAuthor( recommendDTO.getAuthor() );
        recommend.setContent( recommendDTO.getContent() );
        recommend.setRate( recommendDTO.getRate() );

        return recommend;
    }

    @Override
    public List<RecommendDTO> entitiesToDto(List<Recommend> recommends) {
        if ( recommends == null ) {
            return null;
        }

        List<RecommendDTO> list = new ArrayList<RecommendDTO>( recommends.size() );
        for ( Recommend recommend : recommends ) {
            list.add( entityTODto( recommend ) );
        }

        return list;
    }
}
