package com.example.Recommendmicroservice.Mapper;

import com.example.Recommendmicroservice.Entity.Recommend;
import com.example.Recommendmicroservice.MapStructDTO.RecommendDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface RecommendMapper {


    RecommendMapper INSTANCE = Mappers.getMapper(RecommendMapper.class);

    RecommendDTO entityTODto(Recommend recommend);
    Recommend DtoToEntity(RecommendDTO recommendDTO);
    List<RecommendDTO> entitiesToDto(List<Recommend> recommends);
}
