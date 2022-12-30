package com.epam.webdriver.aleksandrgostev.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Optional;

public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitWebDriver() {
        Optional.ofNullable(DRIVER.get()).ifPresent(d -> {
            DRIVER.get().quit();
            DRIVER.set(null);
        });
    }

    public static void createDriver() {
        switch (System.getProperty("browser", "chrome")) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                DRIVER.set(new FirefoxDriver());
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                DRIVER.set(new EdgeDriver());
            }
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                DRIVER.set(new ChromeDriver());
            }
            default: {
                WebDriverManager.chromedriver().setup();
                DRIVER.set(new ChromeDriver());
            }
            DRIVER.get().manage().window().maximize();
            DRIVER.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }
    }
}
