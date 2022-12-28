package com.epam.webdriver.aleksandrgostev.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Optional;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        createDriver();
        return driver.get();
    }

    public static void quitWebDriver() {
        Optional.ofNullable(driver.get()).ifPresent(d -> {
            driver.get().quit();
            driver.set(null);
        });
    }

    private static void createDriver() {
        if (null == driver.get()) {
            switch (System.getProperty("browser", "chrome")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                }
                case "chrome": {
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                }
            }
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }
    }
}
