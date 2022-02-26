package com.example.Recommendmicroservice.Entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recommendations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Recommend {
    private long destId;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long recommendationId;
    private String author;
    private String content;
    private int rate;
    

}
