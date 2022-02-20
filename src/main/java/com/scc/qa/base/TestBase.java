package com.scc.qa.base;

/**
 * @author - Sudhanshu Tripathi
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait explicit_Wait;
	public static WebElement element;
	public static final long WAIT_TIME = 60;

	public TestBase() {
//		File filePath = new File(
//				System.getProperty("user.dir") + "/src/main/resources/config.properties");
		File filePath = new File("./src/main/resources/config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialization(String browserName, String platformName, String versionName, Method name) {
		System.out.println("browser name is : " + browserName);
		String methodName = name.getName();
		if (prop.getProperty("want_To_Execute_On_Local").equalsIgnoreCase("No")) {

			MutableCapabilities sauceOpts = new MutableCapabilities();
			sauceOpts.setCapability("name", methodName);
			sauceOpts.setCapability("build", "Java-W3C-Examples");
			sauceOpts.setCapability("seleniumVersion", "4.1.1");
			sauceOpts.setCapability("username", prop.getProperty("username"));
			sauceOpts.setCapability("accessKey", prop.getProperty("accessKey"));
			sauceOpts.setCapability("tags", "w3c-chrome-tests");

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("sauce:options", sauceOpts);
			cap.setCapability("browserVersion", versionName);
			cap.setCapability("platformName", platformName);

			if (browserName.toLowerCase().equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				cap.setCapability("browserName", "chrome");
			} else if (browserName.toLowerCase().equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				cap.setCapability("browserName", "firefox");
			}
			try {
				driver = new RemoteWebDriver(new URL(prop.getProperty("sauce_Labs_Remote_Webdriver_URL")), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIME));
		System.out.println("Browser launched");
	}

	public static void closingBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
