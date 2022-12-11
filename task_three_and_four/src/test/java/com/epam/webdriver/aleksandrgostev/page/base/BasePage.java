package com.epam.webdriver.aleksandrgostev.page.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private static final Duration SLEEP = Duration.ofSeconds(5);
    protected final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, TIMEOUT, SLEEP);
        PageFactory.initElements(driver, this);
    }

    public void waitForVisibility(List<WebElement> webElements) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public WebElement waitForVisibility(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitAndClick(WebElement webElement) {
        waitForVisibility(webElement).click();
    }
}
