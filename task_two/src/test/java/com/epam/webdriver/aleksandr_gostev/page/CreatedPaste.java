package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class CreatedPaste {
    private WebDriver driver;
    private String pageTitle;

    @FindBy(className = "info-top")
    private WebElement pasteTitle;

    @FindBy(xpath = "//*[text()='Bash']")
    private WebElement pasteHighlightingCode;

    private List<WebElement> pasteCode;

    public CreatedPaste(WebDriver driver, String pageTitle) {
        this.driver = driver;
        this.pageTitle = pageTitle;
        PageFactory.initElements(driver, this);
    }

    public boolean isCreated() {
        pasteTitle.findElement(By.xpath("//*[@class='info-top']/h1"));
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

        pasteCode = driver.findElements(By.className("li1"));
        pasteCode.forEach(item -> result.append(item.getText()));

        return result.toString();
    }
}
