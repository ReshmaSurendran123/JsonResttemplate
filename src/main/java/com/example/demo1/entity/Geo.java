package com.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
//@Embeddable
@AllArgsConstructor
@Entity
@Builder
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id3;
    private String lat;

    private String lng;


    public Geo() {
    }
}
