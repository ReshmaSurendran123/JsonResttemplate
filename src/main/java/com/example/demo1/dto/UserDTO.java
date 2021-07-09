package com.example.demo1.dto;

import com.example.demo1.entity.Address;
import com.example.demo1.entity.Company;
import com.example.demo1.entity.Geo;
import com.example.demo1.entity.Users;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    public int id;
    public String name;
    public String username;
    public String email;
    public AddressDTO address;
    public String phone;
    public String website;
    public CompanyDTO company;

    public UserDTO() {
    }
    public  static  UserDTO valueOf(List<Users> users){
        UserDTO  dto=new UserDTO();
        AddressDTO dto1=new AddressDTO();
        CompanyDTO dto2=new CompanyDTO();
        GeoDTO dto3=new GeoDTO();
        dto2.setBs(users.get(0).getCompany().getBs());
        dto2.setCatchPhrase(users.get(0).getCompany().getCatchPhrase());
        dto2.setName(users.get(0).getCompany().getName());
        dto3.setLat(users.get(0).getAddress().getGeo().getLat());
        dto3.setLng(users.get(0).getAddress().getGeo().getLng());
        dto1.setGeoDTO(dto3);
        dto1.setCity(users.get(0).getAddress().getCity());
        dto1.setStreet(users.get(0).getAddress().getStreet());
        dto1.setSuite(users.get(0).getAddress().getSuite());
        dto1.setZipcode(users.get(0).getAddress().getZipcode());
        dto.setAddress(dto1);
        dto.setCompany(dto2);
        dto.setEmail(users.get(0).getEmail());
        dto.setId(users.get(0).getId());
        dto.setName(users.get(0).getName());
        dto.setPhone(users.get(0).getPhone());
        dto.setUsername(users.get(0).getUsername());
        dto.setWebsite(users.get(0).getWebsite());
        return dto;
    }
    public Users createEntity(){
        Users users=new Users();
        Address address=new Address();
        address.setCity(this.getAddress().getCity());
        address.setStreet(this.getAddress().getStreet());
        address.setSuite(this.getAddress().getSuite());
        address.setZipcode(this.getAddress().getZipcode());
        Geo geo=new Geo();
        geo.setLat(this.getAddress().getGeoDTO().getLat());
        geo.setLng(this.getAddress().getGeoDTO().getLng());
        address.setGeo(geo);
        users.setAddress(address);
        Company company=new Company();
        company.setBs(this.getCompany().getBs());
        company.setCatchPhrase(this.getCompany().getCatchPhrase());
        company.setName(this.getCompany().getName());
        users.setCompany(company);
        users.setEmail(this.getEmail());
        users.setId(this.getId());
        users.setName(this.getName());
        users.setPhone(this.getPhone());
        users.setUsername(this.getUsername());
        users.setWebsite(this.getWebsite());
        return users;
    }

}
