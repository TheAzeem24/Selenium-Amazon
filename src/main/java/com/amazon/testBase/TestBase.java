package com.amazon.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.amazon.webelement.AmazonWebElement;

public class TestBase {

	public static WebDriver driver;
	public static AmazonWebElement amazonWeb;
	public static JavascriptExecutor js;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void setup(String browser, String url) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "D:\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		js = (JavascriptExecutor) driver;
		amazonWeb = new AmazonWebElement(driver);
		driver.manage().window().maximize();
		driver.get(url);
	}

	// takesScreenshot for passed, failed testcase
	public static String takeScreenShot(ITestResult iTestResult) {
		String timeStamp = new SimpleDateFormat("dd-MMM-yy hh.mm.ss aa").format(new Date());
		String dest = System.getProperty("user.dir") + "/Screenshots/" + iTestResult.getName() + "_" + timeStamp
				+ ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}

	// takeScreenshot for each step
	public static void takeScreenShot(String path) {
		String timeStamp = new SimpleDateFormat("dd-MMM-yy hh.mm.ss aa").format(new Date());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "/Test screenshots/" + path + "/" + timeStamp + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
