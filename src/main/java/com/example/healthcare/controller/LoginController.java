package com.example.healthcare.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.healthcare.constant.UrlConst;
import com.example.healthcare.entity.User;
import com.example.healthcare.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final HttpSession session;
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, User user) {
		return "login";
	}
	
	/** 
	 * ログインエラー表示
	 * @param form 入力情報
	 * @return 表示画面 
	 */
	@GetMapping(value = UrlConst.LOGIN, params = "error")
	public String viewWithError(Model model, User user) {
		var errorInfo = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg", errorInfo.getMessage());
		return "login";
	}
	@PostMapping(UrlConst.LOGIN)
	public String login(Model model, User user) {
		var users = userService.findByUserId(user.getUserId());
		var isCorrectUserAuth = users.isPresent() &&
				passwordEncoder.matches(user.getPassword(), users.get().getPassword());
		if (isCorrectUserAuth) {
			return "redirect:/userBmiForm";
		} else {
			model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
			return "login";
		}
	}
}
