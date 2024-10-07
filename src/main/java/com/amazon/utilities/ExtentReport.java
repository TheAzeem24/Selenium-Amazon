package com.amazon.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.amazon.testBase.TestBase;
import com.amazon.webelement.AmazonWebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extentReporter;
	public ExtentTest eTest;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("dd-MMM-yy hh.mm.ss aa").format(new Date());
		String repName = "Test-Report_" + timeStamp + ".html";

		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + repName);
		htmlReporter.config().setDocumentTitle("AutomationTesting");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);

		extentReporter = new ExtentReports();
		extentReporter.attachReporter(htmlReporter);
		extentReporter.setSystemInfo("QA Name", "Azeem");
		extentReporter.setSystemInfo("OS", "Windows 11");
		extentReporter.setSystemInfo("Browser", "edge");
	}

	public void onFinish(ITestContext testContext) {
		extentReporter.flush();
	}

	public void onTestSuccess(ITestResult iTestResult) {
		eTest = extentReporter.createTest(iTestResult.getName());
		eTest.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREEN));
		String takeScreenShot = TestBase.takeScreenShot(iTestResult);
		eTest.addScreenCaptureFromPath(takeScreenShot);
	}

	public void onTestFailure(ITestResult iTestResult) {
		eTest = extentReporter.createTest(iTestResult.getName());
		eTest.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED));
		String takeScreenShot = TestBase.takeScreenShot(iTestResult);
		eTest.addScreenCaptureFromPath(takeScreenShot);
	}

	public void onTestSkipped(ITestResult iTestResult) {
		eTest = extentReporter.createTest(iTestResult.getName());

		eTest.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.AMBER));
	}

}
