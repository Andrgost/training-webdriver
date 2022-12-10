package com.epam.webdriver.aleksandr_gostev.page.google_cloud;

import com.epam.webdriver.aleksandr_gostev.page.BasePage;
import com.epam.webdriver.aleksandr_gostev.page.temp_mail.TempMailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class EmailYourEstimatePage extends BasePage {

    @FindBy(xpath = "//md-input-container/input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//md-dialog-actions/button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public EmailYourEstimatePage(WebDriver driver) {
        super(driver);
    }

    public TempMailPage sendEmail() throws IOException, UnsupportedFlavorException {
        String tempEmailAddress = new TempMailPage(driver)
                .openTempMailPageInNewTab()
                .getTempEmailAddress();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        WebElement mainFrame = driver.findElement(By.xpath("//*[@id='cloud-site']//iframe"));
        driver.switchTo().frame(mainFrame);

        WebElement computeEngineFrame = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(computeEngineFrame);

        emailInput.sendKeys(tempEmailAddress);

        sendEmailButton.click();

        driver.switchTo().window(tabs.get(1));

        return new TempMailPage(driver);
    }
}
