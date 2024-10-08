package com.amazon.search;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.testBase.TestBase;

public class AddToCartPage extends TestBase{
	
	@Test
	public void validateAddToCart()
	{
		String window = "";
		WebElement element = amazonWeb.getsearchResultItems().get(0);
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		while(iterator.hasNext()) {
			window = iterator.next();
		}
		driver.switchTo().window(window);
		amazonWeb.expWait(amazonWeb.getAddToCartBtn(), 5);
		js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getAddToCartBtn());
		amazonWeb.getAddToCartBtn().click();
		amazonWeb.expWait(amazonWeb.getCartBtn(), 5);
		amazonWeb.getCartBtn().click();
		amazonWeb.expWait(amazonWeb.getShoppingCartHeading(), 5);
		String heading = amazonWeb.getShoppingCartHeading().getText();
		Assert.assertEquals(heading, "Shopping Cart");
	}

}
