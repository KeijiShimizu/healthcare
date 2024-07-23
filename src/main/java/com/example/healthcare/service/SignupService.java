package com.example.healthcare.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.healthcare.entity.RoleName;
import com.example.healthcare.entity.SignupForm;
import com.example.healthcare.entity.User;
import com.example.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {
	
	/** アカウント登録テーブルDAO */
	private final UserRepository repository;
	
	/** Dozer Mapper */
	private final Mapper mapper;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報テーブル主キー検索
	 * 
	 * @param loginId ログインID
	 * @return 登録情報(ユーザー情報Entity)、すでに同じユーザーIDで登録がある場合はEmpty
	 */
	
	public Optional<User> registerUser(SignupForm form) {
		var userExisted = repository.findById(form.getUserId());
		if (userExisted.isPresent()) {
			return Optional.empty();
		}
		
		var users = mapper.map(form, User.class);
		
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		users.setPassword(encodedPassword);
		if (users.getRoleName() == null) {
	        users.setRoleName(RoleName.USER); // デフォルトの役割を設定
	    }
		
		return Optional.of(repository.save(users));
	}
}
