package com.example.healthcare.constant;

public class UrlConst {
	
	/** ログイン画面 */
	public static final String LOGIN = "/login";
	
	/** ユーザー登録画面 */
	public static final String REGISTER = "/register";
	
	/** ゲストユーザー画面 */
	public static final String BMI_FORM = "/bmiForm";
	
	/** ゲストユーザー処理結果表示画面 */
	public static final String GUEST_RESULT = "/guestResult";
	
	/** ログインユーザー画面 */
	public static final String USER_BMI_FORM = "/userBmiForm";
	
	/** ログインユーザー処理結果表示画面 */
	public static final String RESULT = "/result";
	
	/** ログインユーザーデータ表画面 */
	public static final String REPORT = "/report";
	
	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = {LOGIN, REGISTER, BMI_FORM, GUEST_RESULT};
}
