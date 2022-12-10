package com.epam.webdriver.aleksandr_gostev.page.temp_mail;

import com.epam.webdriver.aleksandr_gostev.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterPage extends BasePage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[text()='Total Estimated Monthly Cost']/parent::*/following-sibling::*/h3")
    private WebElement costUSD;

    public LetterPage(WebDriver driver) {
        super(driver);
        this.driver = super.getDriver();
    }

    public double getTotalEstimatedMonthlyCost() {
        Pattern pattern = Pattern.compile("\\d[,]\\d+[.]\\d{2}");
        Matcher matcher = pattern.matcher(costUSD.getText());
        String stringPrice = "";
        while (matcher.find()) {
            stringPrice = matcher.group();
        }
        stringPrice = stringPrice.replace(",", "");
        return Double.parseDouble(stringPrice);
    }
}
