package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.driver.DriverManager;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.EmailYourEstimatePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.EstimatePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandrgostev.pages.tempmail.LetterPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TempMailPageTest {

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        DriverManager.getDriver();
    }

    @Test(description = "Verify Provisioning model (VM Class) field")
    @Parameters({"searchTerm", "numberOfInstances", "operatingSystemSoftware",
            "provisioningModel", "machineType", "gpuType",
            "numberOfGPU", "localSSD", "datacenterLocation",
            "datacenterLocationExpected", "machineTypeExpected", "totalPriceExpected"})
    public void checkPriceInEmail(String searchTerm,
                                  int numberOfInstances,
                                  String operatingSystemSoftware,
                                  String provisioningModel,
                                  String machineType,
                                  String gpuType,
                                  String numberOfGPU,
                                  String localSSD,
                                  String datacenterLocation,
                                  String datacenterLocationExpected,
                                  String machineTypeExpected,
                                  String totalPriceExpected) {
        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(DriverManager.getDriver())
                .openPage()
                .searchForTerm(searchTerm)
                .clickSearchResult();

        EstimatePage estimatePage = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation);

        EmailYourEstimatePage emailYourEstimatePage = estimatePage.clickEmailEstimateButton();

        LetterPage letterPage = emailYourEstimatePage.sendEmail().clickLetter();

        Assert.assertTrue(letterPage.getTotalEstimatedMonthlyCost().contains(totalPriceExpected), "Total price is not equal");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        DriverManager.quitWebDriver();
    }
}
