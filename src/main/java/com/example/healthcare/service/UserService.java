package com.example.healthcare.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.healthcare.entity.User;
import com.example.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	
	public Optional<com.example.healthcare.entity.User> findByUserId(String userId) {
		return userRepository.findById(userId);
	}
	
//	// ユーザー情報　新規登録
//	public void create(UserRequest userRequest) {
//		Date now = new Date();
//		User user = new User();
//		user.setName(userRequest.getName());
//		user.setPassword(userRequest.getPassword());
//
//	}
	// ユーザー情報 登録
	public User save(User user) {
        return userRepository.save(user);
    }
	
	public void updateHealthMetricsByUserid(String userId, int age, double height, double weight, String gender) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            user.setAge(age);
            user.setHeight(height);
            user.setWeight(weight);
            user.setGender(gender);
            userRepository.save(user);
        }
    }
}
