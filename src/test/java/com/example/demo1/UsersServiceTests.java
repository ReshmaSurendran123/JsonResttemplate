package com.example.demo1;

import com.example.demo1.Exception.InvalidUserException;
import com.example.demo1.entity.Address;
import com.example.demo1.entity.Company;
import com.example.demo1.entity.Geo;
import com.example.demo1.entity.Users;
import com.example.demo1.repository.UsersRepository;
import com.example.demo1.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.demo1.entity.Users.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UsersServiceTests {
	@InjectMocks
	UsersService service;
	@Mock
	UsersRepository repository;
	/*@Test
	void contextLoads() {
	}*/
	@Mock
	RestTemplate template;
	@Test
	public void listTest() {

		//when
		when(repository.findAll()).thenReturn(Collections.singletonList(getUser()));

		//given
		List<Users> user = service.list();

		//then
		assertEquals(user,Collections.singletonList(getUser()));

	}
	/*@Test
	public void saveUser(){
		when(repository.findById(5)).thenReturn(getUsers());
		String s=service.saveUser(repository.findById(5).get());
		assertEquals("User saved successfully",s);

	}
*/
	@Test
	public void getList1(){
		when(template.getForObject("http://jsonplaceholder.typicode.com/users/",List.class)).
				thenReturn(Collections.singletonList(getUser()));
		List<Users> list=service.list1();
		assertEquals(list,Collections.singletonList(getUser()));
		
	}
	/*public void saveUser(){

	}*/
	@Test
	public void testgetUsers() throws IOException, URISyntaxException {

		when(repository.findByusername("Bret")).thenReturn(getUsers());
			Users list=service.getUsers("Bret");
			assertEquals(list,getUsers());

	}
	@Test
	public void saveUser() {
		when(repository.findById(1)).thenReturn(Optional.of(getUsers()));
		String s=service.saveUser(getUsers());
		assertEquals(s,"User already exists");
	}
	@Test
	public void delete() throws InvalidUserException{
		when(repository.findById(1)).thenReturn(Optional.of(getUsers()));
		String s=service.delete(1);
		assertEquals(s,"User deleted successfully");
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
