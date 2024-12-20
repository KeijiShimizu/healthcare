package com.example.healthcare.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class User implements Serializable {
	@Id
	@Column(name="user_id")
	private String userId;
	private String username;
	private String password;
	private double height;
	private double weight;
	private int age;

	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	private String gender;
	
	

	
}
