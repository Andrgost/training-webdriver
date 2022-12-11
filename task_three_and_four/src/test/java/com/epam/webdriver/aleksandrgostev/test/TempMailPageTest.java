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

public class TempMailPageTest {
    private DriverManager driverManager;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driverManager = new DriverManager();
        driverManager.createWebDriver();
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "fieldValuesDataProvider",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkPriceInEmail(Integer numberOfInstances,
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
        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        EstimatePage estimatePage = calculatorPage.clickComputeEngineTab()
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
        driverManager.quitWebDriver();
    }
}
