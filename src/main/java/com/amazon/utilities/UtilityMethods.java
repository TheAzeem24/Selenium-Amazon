package com.amazon.utilities;

import org.openqa.selenium.By;

import com.amazon.testBase.TestBase;

public class UtilityMethods extends TestBase{
	
	public static void mouseClick(String locator)
	{
		driver.findElement(By.xpath(locator)).click();
	}
	public static void doTypeText(String locator, String input)
	{
		driver.findElement(By.xpath(locator)).sendKeys(input);
	}
	
}
