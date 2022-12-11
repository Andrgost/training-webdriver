package com.epam.webdriver.aleksandrgostev.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasteBinHomePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private final WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteTextArea;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationDropdown;

    @FindBy(id = "postform-name")
    private WebElement pasteNameTitleInput;

    @FindBy(xpath = "//button[contains(@class, 'btn') and contains(@class, '-big')]")
    private WebElement createNewPasteButton;

    public PasteBinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PasteBinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CreatedPaste createPaste(String code, String duration, String name) {
        pasteTextArea.sendKeys(code);

        pasteExpirationDropdown.click();
        WebElement pasteExpirationOption = driver.findElement(By.xpath(String.format("//li[text()='%s']", duration)));
        pasteExpirationOption.click();

        pasteNameTitleInput.sendKeys(name);
        createNewPasteButton.click();

        return new CreatedPaste(driver, name);
    }
}
