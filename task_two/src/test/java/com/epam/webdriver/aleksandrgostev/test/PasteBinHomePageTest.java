package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.pages.CreatedPaste;
import com.epam.webdriver.aleksandrgostev.pages.PasteBinHomePage;
import com.epam.webdriver.aleksandrgostev.testdata.ParametersByDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PasteBinHomePageTest {
    private WebDriver driver;
    private CreatedPaste createdPaste;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new FirefoxDriver();
    }

    @Test(description = "Check tab title is equals to paste name / title", dataProvider = "PageFieldsValues", dataProviderClass = ParametersByDataProvider.class)
    public void checkPageTitle(String code, String highlighting, String expiration, String name) {
        createdPaste = new PasteBinHomePage(driver)
                .openPage()
                .createPaste(code, highlighting, expiration, name);

        Assert.assertTrue(createdPaste.getTabTitle().contains(name), "Page title is not correct.");
    }

    @Test(description = "Check highlighting code", dataProvider = "PageFieldsValues", dataProviderClass = ParametersByDataProvider.class)
    public void checkHighlightingCode(String code, String highlighting, String expiration, String name) {
        createdPaste = new PasteBinHomePage(driver)
                .openPage()
                .createPaste(code, highlighting, expiration, name);

        Assert.assertEquals(createdPaste.getHighlightingCode(), highlighting, "Highlighting code is not correct.");
    }

    @Test(description = "Check highlighting code", dataProvider = "PageFieldsValues", dataProviderClass = ParametersByDataProvider.class)
    public void checkCode(String code, String highlighting, String expiration, String name) {
        createdPaste = new PasteBinHomePage(driver)
                .openPage()
                .createPaste(code, highlighting, expiration, name);

        Assert.assertEquals(createdPaste.getPasteCode(), code, "Code is not correct.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
