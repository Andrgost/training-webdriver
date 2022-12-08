package com.epam.webdriver.aleksandr_gostev.page.google_cloud;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EstimatePage {
    private final WebDriver driver;

    @FindBy(xpath = "//md-list-item/div[contains(text(), 'Spot')]")
    private WebElement provisioningModel;

    @FindBy(xpath = "//md-list-item/div[contains(text(), 'Instance type')]")
    private WebElement machineType;

    @FindBy(xpath = "//md-list/md-list-item/div[contains(text(), 'Region')]")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-list-item/div[contains(text(), 'Local SSD')]")
    private WebElement localSSD;

    @FindBy(xpath = "//div[contains(@class, 'cpc-cart-total')]/h2/b")
    private WebElement totalPrice;

    public EstimatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProvisioningModelText() {
        return provisioningModel.getText();
    }

    public String getMachineTypeText() {
        return machineType.getText();
    }

    public String getDatacenterLocationText() {
        return datacenterLocation.getText();
    }

    public String getLocalSSDOptionText() {
        return localSSD.getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public EmailYourEstimatePage clickEmailEstimateButton() {
        WebElement emailEstimateButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//*[@id='Email Estimate']")));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", emailEstimateButton);
        return new EmailYourEstimatePage(driver);
    }
}
