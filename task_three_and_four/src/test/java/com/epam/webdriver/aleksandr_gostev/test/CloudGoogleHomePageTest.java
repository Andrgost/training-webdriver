package com.epam.webdriver.aleksandr_gostev.test;

import com.epam.webdriver.aleksandr_gostev.page.google_cloud.CloudGoogleHomePage;
import com.epam.webdriver.aleksandr_gostev.page.google_cloud.GoogleCloudPricingCalculatorPage;
import com.epam.webdriver.aleksandr_gostev.testDataParams.ParametersByDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudGoogleHomePageTest {

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
    public void checkProvisioningModel(String provisioningModel,
                                       String machineType,
                                       String localSSD,
                                       String datacenterLocation) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String provisioningModelActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(provisioningModel,
                        machineType,
                        localSSD,
                        datacenterLocation)
                .getProvisioningModelText();

        Assert.assertTrue(provisioningModelActual.contains("Spot"), "Provisioning Model is not equal");
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkMachineType(String provisioningModel,
                                 String machineType,
                                 String localSSD,
                                 String datacenterLocation) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String machineTypeActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(provisioningModel,
                        machineType,
                        localSSD,
                        datacenterLocation)
                .getMachineTypeText();

        Assert.assertTrue(machineTypeActual.contains("n1-standard-8"), "Machine type is not equal");
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkDatacenterLocation(String provisioningModel,
                                        String machineType,
                                        String localSSD,
                                        String datacenterLocation) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String datacenterLocationActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(provisioningModel,
                        machineType,
                        localSSD,
                        datacenterLocation)
                .getDatacenterLocationText();

        Assert.assertTrue(datacenterLocationActual.contains("Salt Lake City"), "Datacenter location is not equal");
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkLocalSSD(String provisioningModel,
                              String machineType,
                              String localSSD,
                              String datacenterLocation) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String localSSDActual = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(provisioningModel,
                        machineType,
                        localSSD,
                        datacenterLocation)
                .getLocalSSDOptionText();

        Assert.assertTrue(localSSDActual.contains("2x375"), "Local SSD is not equal");
    }

    @Test(description = "Verify Provisioning model (VM Class) field",
            dataProvider = "PageFieldsValues",
            dataProviderClass = ParametersByDataProvider.class)
    public void checkTotalPrice(String provisioningModel,
                                String machineType,
                                String localSSD,
                                String datacenterLocation) {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        String totalPrice = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm(provisioningModel,
                        machineType,
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
        double priceActual = Double.parseDouble(stringPrice);

        Assert.assertEquals(priceActual, 2079.78D, "Total price is not equal");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
