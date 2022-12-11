package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.page.CreatedPaste;
import com.epam.webdriver.aleksandrgostev.page.PasteBinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PasteBinHomePageTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new FirefoxDriver();
    }

    @Test(description = "Check creation of the Paste")
    @Parameters({"code", "duration", "name"})
    public void createSimplePaste(String code, String duration, String name) {

        CreatedPaste createdPaste = new PasteBinHomePage(driver)
                .openPage()
                .createPaste(code, duration, name);

        Assert.assertTrue(createdPaste.isCreated(), "Page is not created.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
