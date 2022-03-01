package com.composite.compositeservice;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompositeMapper {
    CompositeMapper instance = Mappers.getMapper(CompositeMapper.class);

    @Mapping(source="dest", target="destination")
    @Mapping(source="revs", target="reviews")
    @Mapping(source="recs", target ="recommendations")
    Composite mapComposite(Destination dest, List<Review> revs, List<Recommend> recs);
}
