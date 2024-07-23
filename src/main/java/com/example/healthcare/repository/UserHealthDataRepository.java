package com.example.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthcare.entity.UserHealthData;

public interface UserHealthDataRepository extends JpaRepository<UserHealthData, Long> {
    List<UserHealthData> findByUserId(String userId);
    
}
