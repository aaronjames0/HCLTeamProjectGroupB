package com.example.Recommendmicroservice.MapStructDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class RecommendDTO {
    private long destId;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long recommendationId;
    private String author;
    private String content;
    private int rate;
}
