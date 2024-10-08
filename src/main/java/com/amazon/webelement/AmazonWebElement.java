package com.amazon.webelement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class AmazonWebElement {
	public WebDriver driver;

	public AmazonWebElement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Search bar
	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchBar;

	public WebElement getSearchBar() {
		return searchBar;
	}

	// search icon
	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	WebElement searchIconBtn;

	public WebElement getSearchIconBtn() {
		return searchIconBtn;
	}

	// search page result count
	@FindBy(xpath = "//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[1]")
	WebElement searchItemPageResult;

	public WebElement getSearchItemPageResult() {
		return searchItemPageResult;
	}

	// searched item
	@FindBy(xpath = "//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[3]")
	WebElement searchResultText;

	public WebElement getSearchResultText() {
		return searchResultText;
	}

	// all items
	@FindAll({
			@FindBy(xpath = "//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']") })
	List<WebElement> searchResultItems;

	public List<WebElement> getsearchResultItems() {
		return searchResultItems;
	}

	// all items price
	@FindAll({
			@FindBy(xpath = "//a[@class = 'a-link-normal s-no-hover s-underline-text s-underline-link-text s-link-style a-text-normal']/span") })
	List<WebElement> itemsPriceList;

	public List<WebElement> getItemsPriceList() {
		return itemsPriceList;
	}

	// first page
	@FindBy(xpath = "//span[@aria-label='Current page, page 1']")
	WebElement firstPage;

	public WebElement getFirstPage() {
		return firstPage;
	}

	// disabled previous button
	@FindBy(xpath = "(//span[@class='s-pagination-item s-pagination-previous s-pagination-disabled '])[1]")
	WebElement disabledPreviousBtn;

	public WebElement getDisabledPreviousBtn() {
		return disabledPreviousBtn;
	}

	// enable previous button
	@FindBy(xpath = "//a[@aria-label='Go to previous page, page 1']")
	WebElement enabledPreviousBtn;

	public WebElement getenabledPreviousBtn() {
		return enabledPreviousBtn;
	}

	// next button
	@FindBy(linkText = "Next")
	WebElement nextBtn;

	public WebElement getNextBtn() {
		return nextBtn;
	}

	// 2nd page
	@FindBy(xpath = "//span[@aria-label='Current page, page 2']")
	WebElement secondPage;
	
	public WebElement getSecondPage() {
		return secondPage;
	}

	public void expWait(WebElement locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	//Brands
	@FindAll({@FindBy (xpath = "//div[@id='brandsRefinements']//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-medium']")})
	List<WebElement> brands;
	
	public List<WebElement> getBrands()
	{
		return brands;
	}
	//Apple brand
	@FindBy(xpath = "//li[@id='p_123/110955']//a[@class='a-link-normal s-navigation-item']")
	WebElement appleBrand;
	
	public WebElement getAppleBrand()
	{
		return appleBrand;
	}
	
	//Apple checkBox
	@FindBy(xpath = "//li[@id='p_123/110955']//input[@type='checkbox']")
	WebElement appleCheckBox;
	
	public WebElement getAppleCheckBox()
	{
		return appleCheckBox;
	}
	
	//add to cart button
	@FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")
	WebElement addToCartBtn;
	
	public WebElement getAddToCartBtn()
	{
		return addToCartBtn;
	}
	
	//cart button
	@FindBy(xpath ="//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")
	WebElement cartBtn;

	public WebElement getCartBtn() {
		return cartBtn;
	}
	
	//shopping cart heading
	@FindBy(xpath ="//h2[normalize-space()='Shopping Cart']")
	WebElement shoppingCartHeading;
		
		public WebElement getShoppingCartHeading()
		{
			return shoppingCartHeading;
		}
	
		
}
