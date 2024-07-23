package com.example.healthcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.healthcare.constant.UrlConst;
import com.example.healthcare.entity.SignupForm;
import com.example.healthcare.service.SignupService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
	
	/** ユーザー登録画面 Service */
	private final SignupService userService;
	
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@GetMapping(UrlConst.REGISTER)
	public String view(Model model, SignupForm form) {
		return "register";
	}
	
	/**
	 * ユーザー登録
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return表示画面
	 */
	@PostMapping(UrlConst.REGISTER)
	public String register(Model model,@Validated SignupForm form, BindingResult bdResult) {
		var user = userService.registerUser(form);
		if (bdResult.hasErrors()) {
			return "register";
		} else {
			return "login";
		}
		
	}
}
