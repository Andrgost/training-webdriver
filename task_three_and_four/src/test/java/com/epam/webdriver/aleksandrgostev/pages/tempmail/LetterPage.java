package com.epam.webdriver.aleksandrgostev.pages.tempmail;

import com.epam.webdriver.aleksandrgostev.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LetterPage extends BasePage {

    @FindBy(xpath = "//*[text()='Total Estimated Monthly Cost']/parent::*/following-sibling::*/h3")
    private WebElement costUSD;

    public LetterPage(WebDriver driver) {
        super(driver);
    }

    public String getTotalEstimatedMonthlyCost() {
        return costUSD.getText();
    }
}
