package com.epam.webdriver.aleksandrgostev.pages.googlecloud;

import com.epam.webdriver.aleksandrgostev.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CloudGoogleHomePage extends BasePage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final By GOOGLE_CLOUD_PRICING_CALCULATOR = By.linkText("Google Cloud Pricing Calculator");

    @FindBy(name = "q")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchResultsButton;

    @FindBy(xpath = "//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]")
    private List<WebElement> searchResults;

    public CloudGoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public CloudGoogleHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CloudGoogleHomePage searchForTerm(String searchTerm) {
        searchButton.click();
        searchButton.sendKeys(searchTerm);
        waitAndClick(searchResultsButton);
        waitForVisibility(searchResults);
        return this;
    }

    public GoogleCloudPricingCalculatorPage clickSearchResult() {
        searchResults.get(1).findElement(GOOGLE_CLOUD_PRICING_CALCULATOR).click();
        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
