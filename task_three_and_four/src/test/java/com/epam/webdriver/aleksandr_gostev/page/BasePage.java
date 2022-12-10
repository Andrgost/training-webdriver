package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitForVisibility(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .visibilityOf(webElement));
    }

    public List<WebElement> waitForPresence(String xpath) {
        return new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public WebElement waitForClick(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .elementToBeClickable(webElement));
    }
}
