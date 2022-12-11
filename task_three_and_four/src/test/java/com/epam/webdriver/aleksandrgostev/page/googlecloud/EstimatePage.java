package com.epam.webdriver.aleksandrgostev.page.googlecloud;

import com.epam.webdriver.aleksandrgostev.page.base.BasePage;
import com.epam.webdriver.aleksandrgostev.utilities.JSExecutorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EstimatePage extends BasePage {

    @FindBy(xpath = "//md-list-item/div[contains(text(), 'Provisioning model')]")
    private WebElement provisioningModel;

    @FindBy(xpath = "//md-list-item/div[contains(text(), 'Instance type')]")
    private WebElement machineType;

    @FindBy(xpath = "//md-list/md-list-item/div[contains(text(), 'Region')]")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-list-item/div[contains(text(), 'Local SSD')]")
    private WebElement localSSD;

    @FindBy(xpath = "//div[contains(@class, 'cpc-cart-total')]/h2/b")
    private WebElement totalPrice;

    @FindBy(xpath = "//*[@id='Email Estimate']")
    private WebElement emailEstimateButton;

    public EstimatePage(WebDriver driver) {
        super(driver);
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
        waitForVisibility(emailEstimateButton);

        JSExecutorUtils.clickElement(driver, emailEstimateButton);
        return new EmailYourEstimatePage(driver);
    }
}
