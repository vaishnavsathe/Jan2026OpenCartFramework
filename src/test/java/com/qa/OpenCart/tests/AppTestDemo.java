package com.qa.OpenCart.tests;
import com.qa.OpenCart.pages.AppTest;

public class AppTestDemo 
{
    public static void main(String[] args) 
    {
        AppTest app = new AppTest();

        app.openApp();
        app.login();
    }
}