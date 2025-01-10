package com.amazon.search;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.amazon.testBase.TestBase;
import com.amazon.utilities.UtilityMethods;

public class Search extends TestBase {

	@Test
	public void validate_Search() {

		// get the method name
		// String methodName = new Exception().getStackTrace()[0].getMethodName();
		String actualText = "iphone";
		amazonWeb.getSearchBar().sendKeys(actualText);
		UtilityMethods.expWait(amazonWeb.getSearchIconBtn(), 5);
		amazonWeb.getSearchIconBtn().click();
		// String totalPageCount = amazonWeb.getSearchItemPageResult().getText();
		amazonWeb.expWait(amazonWeb.getSearchResultText(), 5);
		String SearchResultText = amazonWeb.getSearchResultText().getText();
		String expecedText = SearchResultText.replace("\"", "");
		Assert.assertEquals(actualText, expecedText);

	}

	@Test
	public void validate_SearchResult() {
		validate_Search();
		int resultCount = amazonWeb.getsearchResultItems().size();
		for (int i = 0; i <= resultCount - 1; i++) {
			// js.executeScript("arguments[0].scrollIntoView();",
			// amazonWeb.getsearchResultItems().get(i));
			WebElement webElement = amazonWeb.getsearchResultItems().get(i);
			String text = webElement.getText();
			// System.out.println(text);
			js.executeScript("window.open()");
			Set<String> windowHandles = driver.getWindowHandles();
			Iterator<String> iterator = windowHandles.iterator();
			String next = "";
			while(iterator.hasNext())
			{ 
				next = iterator.next();
				
			}
			driver.switchTo().window(next);
			driver.get("https://www.flipkart.com/");
		}
	}

	@Test
	public void validate_ItemsPriceList() {
		validate_Search();
		int size = amazonWeb.getItemsPriceList().size();
		for (int i = 0; i <= size - 1; i++) {
			js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getItemsPriceList().get(i));
			WebElement itemName = amazonWeb.getsearchResultItems().get(i);
			WebElement itemPrice = amazonWeb.getItemsPriceList().get(i);
			String name = itemName.getText();
			String price = itemPrice.getText();
		}
	}

	@Test
	public void validate_brands() {
		validate_Search();
		js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getAppleBrand());
		amazonWeb.getAppleBrand().click();
		amazonWeb.expWait(amazonWeb.getAppleBrand(), 5);
		js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getAppleBrand());
		boolean selected = amazonWeb.getAppleCheckBox().isSelected();
		assertEquals(selected, true);
	}

	@Test(priority = 5)
	public void validate_Pagination() {
		validate_Search();
		boolean currentPage = false;
		boolean isPreviousBtn = false;
		js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getFirstPage());
		String firstPage = amazonWeb.getFirstPage().getAttribute("aria-label");
		if (firstPage.contains("Current page")) {
			currentPage = true;
		}
		String previousBtn = amazonWeb.getDisabledPreviousBtn().getAttribute("aria-disabled");
		if (previousBtn.equalsIgnoreCase("true")) {
			isPreviousBtn = true;
		}
		Assert.assertEquals(currentPage, true);
		Assert.assertEquals(isPreviousBtn, true);
		amazonWeb.getNextBtn().click();
		currentPage = false;
		isPreviousBtn = false;
		driver.navigate().refresh();
		amazonWeb.expWait(amazonWeb.getNextBtn(), 5);
		js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getNextBtn());
		String secondPage = amazonWeb.getSecondPage().getAttribute("aria-label");
		if (secondPage.contains("Current page")) {
			currentPage = true;
		}
		String attribute = amazonWeb.getenabledPreviousBtn().getAttribute("aria-label");
		if (attribute.equalsIgnoreCase("Go to previous page, page 1")) {
			isPreviousBtn = true;
		}
		Assert.assertEquals(currentPage, true);
		Assert.assertEquals(isPreviousBtn, true);
	}

}
