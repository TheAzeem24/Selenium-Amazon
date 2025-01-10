package com.amazon.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public static void expWait(WebElement element, int sec)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
