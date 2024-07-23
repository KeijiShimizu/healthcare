package com.example.healthcare.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * アカウント登録画面 form
 */
@Data
public class SignupForm {

	/** ログインID */
	@NotNull(message = "ユーザーIDが未入力です。")
	@Length(min = 4, max = 8, message = "4桁以上8桁以内で入力してください。")
	private String userId;
	
	/** パスワード */
	@NotNull(message = "パスワードが未入力です。")
	@Length(min = 8, max = 20, message = "4桁以上20桁以内で入力してください。")
	private String password;
}
