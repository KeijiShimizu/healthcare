/*package com.example.healthcare.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.healthcare.dto.UserRequest;
import com.example.healthcare.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
		com.example.healthcare.entity.User user = userService.findByUsername(username);
		if (user != null && userService.getPasswordEncoder().matches(password, user.getPassword())) {
			model.addAttribute("user", user);
			return "dashboard";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(User user, Model model) {
		userService.save(user);
		model.addAttribute("message", "User registered successfully");
		return "login";
	}*/
	
	/*@GetMapping(value = "/user/list")
	public String displayList(Model model) {
		List<User> userlist = userService.searchAll();
		model.addAttribute("userlist", userlist);
		return "user/list";
	}*/
	/*
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/add";
		}
		// ユーザー情報の登録
		userService.create(userRequest);
		return "redirect:/user/list";
	}
	
}*/
