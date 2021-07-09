
package com.example.demo1.repository;
import com.example.demo1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
     Users findByusername(String username);
}

