package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.User;

public interface UserRepository extends JpaRepository<User,Integer> 
	{
		Optional<User> findUserByEmail(String email);
	}

