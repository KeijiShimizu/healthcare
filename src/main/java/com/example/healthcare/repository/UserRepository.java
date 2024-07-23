package com.example.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthcare.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUserId(String userid);
}
