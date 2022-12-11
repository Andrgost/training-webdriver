package com.epam.webdriver.aleksandrgostev.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class JSExecutorUtils {
    private JSExecutorUtils() {
    }

    public static void clickElementViaJS(WebDriver driver, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }
}