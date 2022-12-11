package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.driver.DriverManager;
import com.epam.webdriver.aleksandrgostev.page.googlecloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandrgostev.page.googlecloud.EmailYourEstimatePage;
import com.epam.webdriver.aleksandrgostev.page.googlecloud.EstimatePage;
import com.epam.webdriver.aleksandrgostev.page.googlecloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandrgostev.page.tempmail.LetterPage;
import com.epam.webdriver.aleksandrgostev.testdata.ParametersByDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TempMailPageTest {
    private DriverManager driverManager;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driverManager = new DriverManager();
        driverManager.createWebDriver();
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
                                  String datacenterLocation,
                                  String provisioningModelExpected,
                                  String machineTypeExpected,
                                  String localSSDExpected,
                                  String datacenterLocationExpected,
                                  Double totalPriceExpected) throws IOException, UnsupportedFlavorException {
        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
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

        Double totalPriceActual = letterPage.getTotalEstimatedMonthlyCost();

        Assert.assertEquals(totalPriceActual, totalPriceExpected, "Total price is not equal");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driverManager.quitWebDriver();
    }
}
