package com.epam.webdriver.aleksandrgostev.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Optional;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(new ChromeDriver());

            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }

        return driver.get();
    }

    public static void quitWebDriver() {
        Optional.ofNullable(driver.get()).ifPresent(d -> {
            driver.get().quit();
            driver.set(null);
        });
    }
}
