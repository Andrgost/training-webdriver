package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class CreatedPaste {
    private final WebDriver driver;
    private final String pageTitle;

    @FindBy(xpath = "//*[@class='info-top']/h1")
    private WebElement pasteTitle;

    @FindBy(xpath = "//*[text()='Bash']")
    private WebElement pasteHighlightingCode;

    @FindBy(className = "li1")
    private List<WebElement> pasteCode;

    public CreatedPaste(WebDriver driver, String pageTitle) {
        this.driver = driver;
        this.pageTitle = pageTitle;
        PageFactory.initElements(driver, this);
    }

    public boolean isCreated() {
        return Objects.equals(pasteTitle.getText(), pageTitle);
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
