package com.qa.amazon;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.scc.qa.base.TestBase;

public class AmazonTest extends TestBase {

	@Parameters({ "browser", "platform", "version" })
	@BeforeMethod
	public void setUp(String browserName, String platformName, String versionName, Method name) {
		initialization(browserName, platformName, versionName, name);
		driver.get(prop.getProperty("applicationURL"));
		explicit_Wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
	}

	// Sample Test
	@Test
	void verifyTitleTest() {
		// Getting the title of the amazon website for validating
		String title = driver.getTitle();
		Assert.assertEquals(title,
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in",
				"Title message is not matched");
	}

	@Test
	void verifyItemAddToCartTest() {
		// Sending value to the search box
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(prop.getProperty("search_Item"));

		// Clicking on the search button
		driver.findElement(By.id("nav-search-submit-button")).click();

		driver.findElement(By.xpath("//span[text()='Apple iPhone 12 (128GB) - Blue']/parent::a")).click();

		// Current window id
		String parentWindowId = driver.getWindowHandle();
		// Waiting for second window tab
		explicit_Wait.until(ExpectedConditions.numberOfWindowsToBe(2)).booleanValue();

		Set<String> allWindowTabs = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<String>(allWindowTabs);
		// Switching to another window
		driver.switchTo().window(tabs.get(1));

		explicit_Wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();

		// Waiting for the Added to Cart message
		explicit_Wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[text()='Added to Cart']")));

		// Clicking on the close button of the side window
		driver.findElement(By.id("attach-close_sideSheet-link")).click();

		String numberOfItemsInCart = explicit_Wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count"))).getText();
		driver.close();
		// Switching to parent window
		driver.switchTo().window(parentWindowId);
		Assert.assertEquals(Integer.parseInt(numberOfItemsInCart), 1, "Item is not added to the amazon cart");
	}

	@AfterMethod
	void tearDown() {
		// Closing all the window
		closingBrowser();
	}

}
