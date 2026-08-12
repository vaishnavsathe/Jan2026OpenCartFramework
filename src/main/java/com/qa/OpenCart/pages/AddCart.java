package com.qa.OpenCart.pages;

import org.openqa.selenium.By;

public class AddCart 
{
	private By cart = By.id("cart");
	public void addcart()
	{
		System.out.println("Adding to the cart method"+cart);
	}
}
