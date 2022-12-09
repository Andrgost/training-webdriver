package com.epam.webdriver.aleksandr_gostev.page.temp_mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class TempMailPage {
    private final String TEMP_MAIL_URL = "https://tempmail.plus/";
    private final WebDriver driver;

    @FindBy(id = "pre_copy")
    private WebElement copyMailAddressButton;

    @FindBy(xpath = "//*[@class='inbox']/div[@class='mail']")
    private WebElement letter;

    public TempMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TempMailPage openTempMailPageInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(TEMP_MAIL_URL);
        return this;
    }

    public String getTempEmailAddress() throws IOException, UnsupportedFlavorException {
        copyMailAddressButton.click();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }

    public LetterPage clickLetter() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .elementToBeClickable(letter));
        letter.click();
        return new LetterPage(driver);
    }
}
