package com.qa.OpenCart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final int DEFAULT_TIMEOUT = 5;
	public static final int MED_DEFAULT_TIMEOUT = 10;
	public static final int LONG_DEFAULT_TIMEOUT = 15;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCTS_PAGE_FRACTION_URL = "route=account/account";
	
	public static List<String> expHeadersList = Arrays.asList("My Account",
															  "My Orders",
															  "My Affiliate Account",
															  "Newsletter");
	
	
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	
	//******************Sheet name***************/
	public static final String REGISTER_SHEET_NAME = "Register";
	public static final String PRODUCT_SHEET_NAME = "Product";
	public static final String IMAGES_SHEET_NAME = "Images";
	
}