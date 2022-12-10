package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class CreatedPaste {
    private final WebDriver driver;
    private final String pageTitle;

    @FindBy(xpath = "//*[@class='info-top']/h1")
    private WebElement pasteTitle;

    public CreatedPaste(WebDriver driver, String pageTitle) {
        this.driver = driver;
        this.pageTitle = pageTitle;
        PageFactory.initElements(driver, this);
    }

    public boolean isCreated() {
        return Objects.equals(pasteTitle.getText(), pageTitle);
    }
}
