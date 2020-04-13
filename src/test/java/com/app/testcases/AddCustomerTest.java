package com.app.testcases;

import com.app.utils.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseClass{
    @Test
    public void bankManagerLogin() {
        log.debug("Inside Login As Bank Manager test");
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("acBtn"))));
    }

    @Test
    public void createCustomer() {
        log.debug("Inside Create Customer test");
        Reporter.log("Inside create customer");
        driver.findElement(By.cssSelector(OR.getProperty("acBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("fnTxt"))).sendKeys("Parvathy");
        driver.findElement(By.cssSelector(OR.getProperty("lnTxt"))).sendKeys("Purushothamna");
        driver.findElement(By.cssSelector(OR.getProperty("pcTxt"))).sendKeys("m2n7g9");
        driver.findElement(By.cssSelector(OR.getProperty("submitBtn"))).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains("Customer added successfully"));
        alert.accept();
//        Customer added successfully
    }
}
