package com.amazon.search;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.amazon.testBase.TestBase;

public class Search extends TestBase {

	@Test(priority = 1)
	public void validate_Search() {

		// get the method name
		// String methodName = new Exception().getStackTrace()[0].getMethodName();
		String actualText = "iphone";
		amazonWeb.getSearchBar().sendKeys(actualText);
		amazonWeb.getSearchIconBtn().click();
		// String totalPageCount = amazonWeb.getSearchItemPageResult().getText();
		amazonWeb.expWait(amazonWeb.getSearchResultText(), 5);
		String SearchResultText = amazonWeb.getSearchResultText().getText();
		String expecedText = SearchResultText.replace("\"", "");
		Assert.assertEquals(actualText, expecedText);

	}

	@Test(priority = 2)
	public void validate_SearchResult() {

		int resultCount = amazonWeb.getsearchResultItems().size();
		for (int i = 0; i <= resultCount - 1; i++) {
			// js.executeScript("arguments[0].scrollIntoView();",
			// amazonWeb.getsearchResultItems().get(i));
			WebElement webElement = amazonWeb.getsearchResultItems().get(i);
			String text = webElement.getText();
			// System.out.println(text);
		}
	}

	@Test(priority = 3, dependsOnMethods = { "validate_Search" })
	public void validate_ItemsPriceList() {
		int size = amazonWeb.getItemsPriceList().size();
		for (int i = 0; i <= size - 1; i++) {
			js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getItemsPriceList().get(i));
			WebElement itemName = amazonWeb.getsearchResultItems().get(i);
			WebElement itemPrice = amazonWeb.getItemsPriceList().get(i);
			String name = itemName.getText();
			String price = itemPrice.getText();
		}
	}
	
	@Test(priority = 4,dependsOnMethods = {"validate_Search"})
	public void validate_brands()
	{
		WebElement SelectedBrand;
		boolean selected = false;
		//js.executeScript("arguments[0].scrollIntoView();", amazonWeb.getBrands());
		amazonWeb.getBrandsSeeMore().click();
		int brandSize = amazonWeb.getBrands().size();
		for(int i=0;i<=brandSize-1;i++)
		{
			SelectedBrand = amazonWeb.getBrands().get(i);
			String brandName = SelectedBrand.getText();
			if(brandName.equalsIgnoreCase("Apple"))
			{
				amazonWeb.expWait(SelectedBrand, 5);
				js.executeScript("arguments[0].scrollIntoView();", SelectedBrand);
				SelectedBrand.click();
				selected = SelectedBrand.isSelected();
				break;
			}
		}
		Assert.assertEquals(selected, true);
	}

	@Test(priority = 5)
	public void validate_Pagination() {

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
