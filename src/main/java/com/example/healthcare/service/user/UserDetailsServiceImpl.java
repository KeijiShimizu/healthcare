package com.example.healthcare.service.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.healthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報生成
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	/** ユーザー情報テーブルRepository */
	private final UserRepository repository;
	
	/** ユーザー情報生成
	 * 
	 * @param username ログインID
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findById(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		return User.withUsername(user.getUserId())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}

	
}
