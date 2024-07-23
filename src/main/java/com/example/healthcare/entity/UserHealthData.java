package com.example.healthcare.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_health_data")
@Data
public class UserHealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "age")
    private int age;
    
    @Column(name = "gender")
    private String gender;

    @Column(name = "bmi")
    private double bmi;

    @Column(name = "body_fat_percentage")
    private double bodyFatPercentage;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

}
