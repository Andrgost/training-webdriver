package com.epam.webdriver.aleksandrgostev.pages.googlecloud;

import com.epam.webdriver.aleksandrgostev.pages.tempmail.TempMailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class EmailYourEstimatePage extends GoogleCloudPricingCalculatorPage {

    @FindBy(xpath = "//md-input-container/input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//md-dialog-actions/button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public EmailYourEstimatePage(WebDriver driver) {
        super(driver);
    }

    public TempMailPage sendEmail() {
        String tempEmailAddress = new TempMailPage(driver)
                .openTempMailPageInNewTab()
                .getTempEmailAddress();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        driver.switchTo().frame(driver.findElement(MAIN_FRAME));

        driver.switchTo().frame(driver.findElement(COMPUTE_ENGINE_FRAME));

        emailInput.sendKeys(tempEmailAddress);

        sendEmailButton.click();

        driver.switchTo().window(tabs.get(1));

        return new TempMailPage(driver);
    }
}
