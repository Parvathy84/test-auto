package com.app.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;
    public static Properties testConfig = new Properties();
    public static Properties OR = new Properties();
    public static Properties  config= new Properties();
    public static Logger log = Logger.getLogger(BaseClass.class.getName());
    public static WebDriverWait wait;
    public ExtentReports rep = ExtentUtils.getInstance();
    public static ExcelUtils excel = new ExcelUtils(
            System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static ExtentTest test;
    public static String browser;

    @BeforeSuite
    public void setUp() {
        if (driver == null)
            try {
                testConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources" +
                        "/properties/test-config.properties"));
                OR.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources" +
                        "/properties/OR.properties"));
                config.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources" +
                        "/properties/config.properties"));
                log.debug("Confif files loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }
        switch (testConfig.getProperty("browser").toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--kiosk");
                driver = new ChromeDriver(options);
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                //Error
        }
        log.debug("Browser launched");
        driver.manage().deleteAllCookies();
        driver.get(testConfig.getProperty("testurl"));
        log.debug("Navigated to " + testConfig.getProperty("testurl"));
//        driver.manage().window().maximize();;
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(testConfig.getProperty("implicit.wait")),
                TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(testConfig.getProperty("pageload.wait")),
                TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterSuite
    public void tearDOwn() {
        if (driver != null) {
            driver.close();
        }
        log.debug("Test execution completed");
    }
}
