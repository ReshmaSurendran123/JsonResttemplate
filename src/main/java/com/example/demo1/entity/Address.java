package com.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
//@Embeddable
@Entity
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id1;
    private String street;

    private String suite;

    private String city;

    private String zipcode;

    //@Embedded
    @OneToOne(cascade=CascadeType.ALL)
    //JoinColumn the corresponding table name(geo)
    @JoinColumn(name="geo_id")
    private Geo geo;

    public Address() {
    }

}

