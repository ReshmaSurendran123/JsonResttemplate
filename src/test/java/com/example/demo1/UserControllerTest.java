package com.example.demo1;

import com.example.demo1.Exception.InvalidUserException;
import com.example.demo1.controller.UserController;
import com.example.demo1.entity.Address;
import com.example.demo1.entity.Company;
import com.example.demo1.entity.Geo;
import com.example.demo1.entity.Users;
import com.example.demo1.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static com.example.demo1.entity.Users.builder;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController controller;
    @Mock
    UsersService service;
    @Test
    public void testgetUsers() throws IOException, URISyntaxException {
        when(service.getUsers("Bret")).thenReturn(getUsers());
        Users users=controller.getUsers("Bret");
        assertEquals(users,getUsers());
    }
    @Test
    public void list(){
        when(service.list()).thenReturn(Collections.singletonList(getUser()));
        List<Users> users=controller.list();
        assertEquals(users,Collections.singletonList(getUser()));
    }
    @Test
    public void list1(){
        when(service.list1()).thenReturn(Collections.singletonList(getUser()));
        List<Users> users=controller.list1();
        assertEquals(users,Collections.singletonList(getUser()));
    }
    @Test
    public void delete() throws InvalidUserException{
        when(service.delete(1)).thenReturn("User deleted successfully");
        String s=controller.delete(1);
        assertEquals(s,"User deleted successfully");
    }

    @Test
    public void saveUser() throws IOException{
        when(service.saveUser(getUsers())).thenReturn("User already exists");
        String s=controller.saveUser(getUsers());
        assertEquals(s,"User already exists");

    }
    private Address getAddress(){
        return Address.builder().id1(0).city("Gwenborough").street("Kulas Light").suite("Apt. 556").zipcode("92998-3874")
                .geo(getGeo()).build();
    }
    private Geo getGeo(){
        return Geo.builder().id3(0).lat("-37.3159").lng("81.1496").build();
    }
    private Company getCompany(){
        return Company.builder().id2(0).bs("harness real-time e-markets").catchPhrase("Multi-layered client-server neural-net").name("Romaguera-Crona").build();
    }
    private Users getUser(){
        return builder().id(1).username("Bret").email("Sincere@april.biz")
                .name("Leanne Graham").phone("1-770-736-8031 x56442").
                        website("hildegard.org").address(null).company(null).build();
    }

    private Users getUsers(){

        return builder().id(1).username("Bret").email("Sincere@april.biz")
                .name("Leanne Graham").phone("1-770-736-8031 x56442").
                        website("hildegard.org").address(getAddress()).company(getCompany()).build();
    }
}
