package com.epam.webdriver.aleksandr_gostev.page;

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
    private WebDriver driver;

    private final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";

    @FindBy(name = "q")
    private WebElement searchButton;

    //@FindBy(xpath = "//button[@type='submit']")
    private WebElement searchResultsButton;
    //@FindBy(id = "suggestion-partial-query-0")
    //private WebElement searchPartialResultLink;

    @FindBy(linkText = "Google Cloud Platform Pricing Calculator")
    private WebElement searchLink;

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
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        searchResultsButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchResultsButton.click();

        waitForSearchResults();
/*
        List<WebElement> searchResultLinks = driver.findElements(By
                .xpath("//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]"));

        for (WebElement link : searchResultLinks) {
            String linkText = link.findElement(By.tagName("b")).getText();
            System.out.println("Link text = " + linkText);
            if (linkText.equals("Google Cloud Platform Pricing Calculator")) {
                System.out.println("I'm here");
                clickSearchResult();
                return this;
            }
        }
        */

        //driver.findElement(By.xpath("//*[@aria-label='Page 2']")).click();
        //System.out.println("Page 2 is clicked");

        //waitForSearchResults();
        /*
        List<WebElement> searchResultLinks2 = driver.findElements(By
                .xpath("//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]"));
        for (WebElement link : searchResultLinks2) {
            String linkText = link.findElement(By.tagName("b")).getText();
            System.out.println("Link text = " + linkText);
            if (linkText.equals("Google Cloud Platform Pricing Calculator")) {
                System.out.println("I'm here 2");
                clickSearchResult();
                return this;
            }
        }
        */

        return this;
    }

    public GoogleCloudPricingCalculatorPage clickSearchResult() {
        /*
        WebElement googleCalculator = driver.findElement(By.linkText("Google Cloud Platform Pricing Calculator"));
        googleCalculator.click();
        */
        List<WebElement> searchResultLinks = driver.findElements(By
                .xpath("//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]"));
        System.out.println(searchResultLinks.get(1).getText());
        searchResultLinks.get(1).findElement(By.linkText("Google Cloud Pricing Calculator")).click();

        return new GoogleCloudPricingCalculatorPage(driver);
    }

    private void waitForSearchResults() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By
                                .xpath("//div[contains(@class, 'gsc-webResult') and contains(@class, 'gsc-result')]")));
        //return this;
    }
}
