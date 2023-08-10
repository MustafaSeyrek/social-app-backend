package com.example.socialapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);
 
}
