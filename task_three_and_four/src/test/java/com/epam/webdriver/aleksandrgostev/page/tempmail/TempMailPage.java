package com.epam.webdriver.aleksandrgostev.page.tempmail;

import com.epam.webdriver.aleksandrgostev.page.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TempMailPage extends BasePage {
    private static final String TEMP_MAIL_URL = "https://tempmail.plus/";

    @FindBy(id = "pre_copy")
    private WebElement copyMailAddressButton;

    @FindBy(xpath = "//*[@class='inbox']/div[@class='mail']")
    private WebElement letter;

    public TempMailPage(WebDriver driver) {
        super(driver);
    }

    public TempMailPage openTempMailPageInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(TEMP_MAIL_URL);
        return this;
    }

    public String getTempEmailAddress() {
        copyMailAddressButton.click();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        try {
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            return null;
        }
    }

    public LetterPage clickLetter() {
        waitAndClick(letter);
        return new LetterPage(driver);
    }
}
