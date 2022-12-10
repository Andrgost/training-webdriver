package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private static final Duration SLEEP = Duration.ofSeconds(5);
    protected final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(ExpectedCondition<?> expectedCondition) {
        new WebDriverWait(driver, TIMEOUT, SLEEP).until(expectedCondition);
    }

    public void waitForVisibility(WebElement webElement) {
        waitForElement(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForVisibilityAll(List<WebElement> webElements) {
        waitForElement(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void waitForClick(WebElement webElement) {
        waitForElement(ExpectedConditions.elementToBeClickable(webElement));
    }
}
