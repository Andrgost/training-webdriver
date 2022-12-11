package com.epam.webdriver.aleksandrgostev.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreatedPaste {
    private final WebDriver driver;

    @FindBy(xpath = "//*[text()='Bash']")
    private WebElement pasteHighlightingCode;

    @FindBy(className = "li1")
    private List<WebElement> pasteCode;

    public CreatedPaste(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTabTitle() {
        return driver.getTitle();
    }

    public String getHighlightingCode() {
        return pasteHighlightingCode.getText();
    }

    public String getPasteCode() {
        StringBuilder result = new StringBuilder();

        pasteCode.forEach(item -> result.append(item.getText()));

        return result.toString();
    }
}
