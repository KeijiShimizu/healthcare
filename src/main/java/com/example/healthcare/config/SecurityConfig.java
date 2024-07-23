package com.example.healthcare.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.healthcare.constant.UrlConst;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(
					authorize -> authorize
						.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
						.anyRequest().authenticated()
			)
			.formLogin(formLogin ->
				formLogin
					.loginPage(UrlConst.LOGIN)
					.usernameParameter("userId")
					.permitAll()
					.defaultSuccessUrl(UrlConst.USER_BMI_FORM)
			)
			.logout(logout ->
				logout
					.permitAll()
			);
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	Mapper mapper() {
		return new DozerBeanMapper();
	}
}
