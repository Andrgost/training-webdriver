package com.epam.webdriver.aleksandr_gostev.page.google_cloud;

import com.epam.webdriver.aleksandr_gostev.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CloudGoogleHomePage extends BasePage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";

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

    public CloudGoogleHomePage searchForTerm() {
        searchButton.click();
        searchButton.sendKeys(SEARCH_TERM);

        waitForVisibility(searchResultsButton);
        searchResultsButton.click();

        waitForVisibilityAll(searchResults);

        return this;
    }

    public GoogleCloudPricingCalculatorPage clickSearchResult() {
        searchResults.get(1).findElement(By.linkText("Google Cloud Pricing Calculator")).click();

        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
