package com.example.demo1.controller;

import com.example.demo1.Exception.InvalidUserException;
import com.example.demo1.dto.UserDTO;
import com.example.demo1.entity.Users;
import com.example.demo1.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UsersService service ;

    @GetMapping(value = "/users", produces = "application/json")
    public Users getUsers(@RequestParam  @Pattern(regexp = "[A-Z]{1}[a-z]*",
            message = "username is invalid") String username) throws IOException, URISyntaxException {
        return service.getUsers(username);

    }
    @GetMapping("/list")
    public List<Users> list() {
        return service.list();
    }

    @GetMapping("/list1")
    public List<Users> list1() {
        return service.list1();
    }

    @DeleteMapping("/deletelist")
    public String delete(@RequestParam int id) throws InvalidUserException {
        return service.delete(id);
    }
    @PostMapping("/savelist")
    public String saveUser(@RequestBody Users user) throws IOException {
        return service.saveUser(user);
    }

    }