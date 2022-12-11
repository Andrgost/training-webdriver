package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.driver.DriverManager;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.EstimatePage;
import com.epam.webdriver.aleksandrgostev.pages.googlecloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandrgostev.testdata.ParametersByDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.lang.String.format;

public class CloudGoogleHomePageTest {

    private static final String ERROR_MESSAGE = "%s is not as expected";
    private final DriverManager driverManager = new DriverManager();

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driverManager.createWebDriver();
    }

    @Test(description = "Verify Datacenter Location field",
            dataProvider = "fieldValuesDataProvider",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkDatacenterLocation(String searchTerm,
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
        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
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

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(estimatePage.getDatacenterLocationText().contains(datacenterLocationExpected), format(ERROR_MESSAGE, "Datacenter location"));
        softAssert.assertTrue(estimatePage.getProvisioningModelText().contains(provisioningModel), format(ERROR_MESSAGE, "Provisioning model"));
        softAssert.assertTrue(estimatePage.getMachineTypeText().contains(machineTypeExpected), format(ERROR_MESSAGE, "Machine type"));
        softAssert.assertTrue(estimatePage.getLocalSSDOptionText().contains(localSSD), format(ERROR_MESSAGE, "Local SSD"));
        softAssert.assertTrue(estimatePage.getTotalPrice().contains(totalPriceExpected), format(ERROR_MESSAGE, "Total price"));
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driverManager.quitWebDriver();
    }
}
