package com.epam.webdriver.aleksandrgostev.test;

import com.epam.webdriver.aleksandrgostev.driver.DriverManager;
import com.epam.webdriver.aleksandrgostev.page.googlecloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandrgostev.page.googlecloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandrgostev.testdata.ParametersByDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudGoogleHomePageTest {

    private DriverManager driverManager;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driverManager = new DriverManager();
        driverManager.createWebDriver();
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkProvisioningModel(Integer numberOfInstances,
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
                                       Double totalPriceExpected) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String provisioningModelActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        whatAreTheseInstancesFor,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpu,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation)
                .getProvisioningModelText();

        Assert.assertTrue(provisioningModelActual.contains(provisioningModelExpected), "Provisioning Model is not equal");
    }

    @Test(description = "Verify Machine Type field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkMachineType(Integer numberOfInstances,
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
                                 Double totalPriceExpected) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String machineTypeActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        whatAreTheseInstancesFor,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpu,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation)
                .getMachineTypeText();

        Assert.assertTrue(machineTypeActual.contains(machineTypeExpected), "Machine type is not equal");
    }

    @Test(description = "Verify Datacenter Location field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkDatacenterLocation(Integer numberOfInstances,
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
                                        Double totalPriceExpected) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String datacenterLocationActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        whatAreTheseInstancesFor,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpu,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation)
                .getDatacenterLocationText();

        Assert.assertTrue(datacenterLocationActual.contains(datacenterLocationExpected), "Datacenter location is not equal");
    }

    @Test(description = "Verify Local SSD field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkLocalSSD(Integer numberOfInstances,
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
                              Double totalPriceExpected) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String localSSDActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        whatAreTheseInstancesFor,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpu,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation)
                .getLocalSSDOptionText();

        Assert.assertTrue(localSSDActual.contains(localSSDExpected), "Local SSD is not equal");
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkTotalPrice(Integer numberOfInstances,
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
                                Double totalPriceExpected) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driverManager.getDriver())
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String totalPrice = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(numberOfInstances,
                        whatAreTheseInstancesFor,
                        operatingSystemSoftware,
                        provisioningModel,
                        machineType,
                        gpu,
                        gpuType,
                        numberOfGPU,
                        localSSD,
                        datacenterLocation)
                .getTotalPrice();

        Pattern pattern = Pattern.compile("\\d[,]\\d+[.]\\d{2}");
        Matcher matcher = pattern.matcher(totalPrice);
        String stringPrice = "";
        while (matcher.find()) {
            stringPrice = matcher.group();
        }
        stringPrice = stringPrice.replace(",", "");
        Double priceActual = Double.parseDouble(stringPrice);

        Assert.assertEquals(priceActual, totalPriceExpected, "Total price is not equal");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driverManager.quitWebDriver();
    }
}
