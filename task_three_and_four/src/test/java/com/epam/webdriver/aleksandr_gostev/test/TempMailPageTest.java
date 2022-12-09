package com.epam.webdriver.aleksandr_gostev.test;

import com.epam.webdriver.aleksandr_gostev.page.google_cloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandr_gostev.page.google_cloud.EmailYourEstimatePage;
import com.epam.webdriver.aleksandr_gostev.page.google_cloud.EstimatePage;
import com.epam.webdriver.aleksandr_gostev.page.google_cloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandr_gostev.page.temp_mail.LetterPage;
import com.epam.webdriver.aleksandr_gostev.testDataParams.ParametersByDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class TempMailPageTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkPriceInEmail(Integer numberOfInstances,
                                  String whatAreTheseInstancesFor,
                                  String operatingSystemSoftware,
                                  String provisioningModel,
                                  String machineType,
                                  Boolean gpu,
                                  String gpuType,
                                  String numberOfGPU,
                                  String localSSD,
                                  String datacenterLocation) throws IOException, UnsupportedFlavorException {
        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        EstimatePage estimatePage = calculatorPage.clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        whatAreTheseInstancesFor,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpu,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation);

        EmailYourEstimatePage emailYourEstimatePage = estimatePage.clickEmailEstimateButton();

        LetterPage letterPage = emailYourEstimatePage.sendEmail()
                .clickLetter();

        double totalPriceActual = letterPage.getTotalEstimatedMonthlyCost();

        Assert.assertEquals(totalPriceActual, 2079.78D, "Total price is not equal");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
