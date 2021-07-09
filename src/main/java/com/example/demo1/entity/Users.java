package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
public class Users {
    @Id
    public int id;
    public String name;
    public String username;
    public String email;
    //@Embedded
    @OneToOne(cascade=CascadeType.ALL)
    //JoinColumn the corresponding table name(address)
    @JoinColumn(name="address_id")
    private Address address;
    public String phone;
    public String website;
    //@Embedded
    //JoinColumn the corresponding table name(company)
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    public Company company;

    public Users() {
    }

}


