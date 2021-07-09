package com.example.demo1.service;

import com.example.demo1.Exception.InvalidUserException;
import com.example.demo1.dto.AddressDTO;
import com.example.demo1.dto.CompanyDTO;
import com.example.demo1.dto.GeoDTO;
import com.example.demo1.dto.UserDTO;
import com.example.demo1.entity.Users;
import com.example.demo1.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;
    RestTemplate template=new RestTemplate();
    String url = "http://jsonplaceholder.typicode.com/users/";
    public Users getUsers(String username) throws IOException, URISyntaxException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("username", username);
        Users[] users=template.getForObject(new URI(builder.toUriString()),Users[].class);

        /*ObjectMapper mapper=new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Users[] dto=mapper.readValue(new URL(builder.toUriString()),Users[].class);*/
        UserDTO dto1=UserDTO.valueOf(Arrays.asList(users));
        Users dto2=repository.findByusername(username);
        System.out.println(users);
        if(Objects.isNull(dto2)) {
            repository.save(dto1.createEntity());
        }
    return dto1.createEntity();
        //return mapper.readValue(new URL(builder.toUriString()),UserDTO[].class);
    }
    public List<Users> list() {
    return repository.findAll();
    }
    public List<Users> list1() {
        List<Users> users=template.getForObject(url,List.class);
        return users;
    }

    public String saveUser(Users user){
        Optional<Users> dto2=repository.findById(user.getId());
        if(!dto2.isPresent()) {
            repository.save(user);
            return "User saved successfully";
        }
        return "User already exists";
    }

public String delete(int id) throws InvalidUserException
{
    Optional<Users> users=repository.findById(id);
    if(!users.isPresent()) {
        throw new InvalidUserException("No such user exists");
    }
    else {
        repository.deleteById(id);
        return "User deleted successfully";
    }
}


}

