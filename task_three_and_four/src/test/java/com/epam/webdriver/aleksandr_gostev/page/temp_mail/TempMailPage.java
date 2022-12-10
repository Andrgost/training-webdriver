package com.epam.webdriver.aleksandr_gostev.page.temp_mail;

import com.epam.webdriver.aleksandr_gostev.page.BasePage;
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

    public String getTempEmailAddress() throws IOException, UnsupportedFlavorException {
        copyMailAddressButton.click();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }

    public LetterPage clickLetter() {
        waitForClick(letter);

        letter.click();
        return new LetterPage(driver);
    }
}
