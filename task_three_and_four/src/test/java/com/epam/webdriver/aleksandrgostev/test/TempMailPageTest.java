package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.driver.DriverManager;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.EmailYourEstimatePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.EstimatePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandrgostev.pages.tempmail.LetterPage;
import com.epam.webdriver.aleksandrgostev.utilities.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class TempMailPageTest extends BaseTest {

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
}
