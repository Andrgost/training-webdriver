package com.epam.webdriver.aleksandr_gostev.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver createWebDriver() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        return driver;
    }

    public void quitWebDriver() {
        driver.quit();
        driver = null;
    }
}
