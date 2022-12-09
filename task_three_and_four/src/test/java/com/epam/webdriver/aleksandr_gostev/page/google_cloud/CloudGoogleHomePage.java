package com.epam.webdriver.aleksandr_gostev.page.google_cloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CloudGoogleHomePage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private final WebDriver driver;

    private final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";

    @FindBy(name = "q")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchResultsButton;

    public CloudGoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CloudGoogleHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CloudGoogleHomePage searchForTerm() {
        searchButton.click();
        searchButton.sendKeys(SEARCH_TERM);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(searchResultsButton));
        searchResultsButton.click();

        waitForSearchResults();

        return this;
    }

    public GoogleCloudPricingCalculatorPage clickSearchResult() {
        List<WebElement> searchResultLinks = driver.findElements(By
                .xpath("//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]"));

        searchResultLinks.get(1).findElement(By.linkText("Google Cloud Pricing Calculator")).click();

        return new GoogleCloudPricingCalculatorPage(driver);
    }

    private void waitForSearchResults() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By
                                .xpath("//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]")));
    }
}
