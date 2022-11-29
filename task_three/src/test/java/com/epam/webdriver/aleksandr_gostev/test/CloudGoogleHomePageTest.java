package com.epam.webdriver.aleksandr_gostev.test;

import com.epam.webdriver.aleksandr_gostev.page.CloudGoogleHomePage;
import com.epam.webdriver.aleksandr_gostev.page.GoogleCloudPricingCalculatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CloudGoogleHomePageTest {

    private WebDriver driver;

    private final String PROVISIONING_MODEL = "Spot";
    private final String INSTANCE_TYPE = "n1-standard-8";
    private final String DATACENTER_LOCATION = "Frankfurt";
    private final String LOCAL_SSD = "2x375 GiB";
    private final double TOTAL_PRICE = 2563.32D;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new FirefoxDriver();
    }

    @Test(description = "Verify Provisioning model (VM Class) field")
    public void checkProvisioningModel() throws InterruptedException {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        WebElement provisioningModel = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm()
                .getProvisioningModel();

        String provisioningModelValue = provisioningModel.getText();
        //System.out.println(provisioningModelValue);
        Assert.assertTrue(provisioningModelValue.contains(PROVISIONING_MODEL), "Provisioning Model is not equal");
        //Assert.assertEquals("test", "ttt");
    }
/*
    @Test(description = "Verify Machine type (Instance type) field")
    public void checkMachineType() throws InterruptedException {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        WebElement machineType = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm()
                .getProvisioningModel();

        String machineTypeValue = machineType.getText();

        Assert.assertTrue(machineTypeValue.contains(INSTANCE_TYPE), "Machine type is not equal");
    }

    @Test(description = "Verify Datacenter location (Region) field")
    public void checkDatacenterLocation() throws InterruptedException {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        WebElement datacenterLocation = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm()
                .getProvisioningModel();

        String datacenterLocationValue = datacenterLocation.getText();

        Assert.assertTrue(datacenterLocationValue.contains(DATACENTER_LOCATION), "Datacenter location is not equal");
    }

    @Test(description = "Verify Local SSD field")
    public void checkLocalSSD() throws InterruptedException {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        WebElement localSSD = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm()
                .getProvisioningModel();

        String localSSDValue = localSSD.getText();

        Assert.assertTrue(localSSDValue.contains(LOCAL_SSD), "Local SSD is not equal");
    }

    @Test(description = "Verify Cost value")
    public void checkCost() throws InterruptedException {

        GoogleCloudPricingCalculatorPage calculatorPage = new CloudGoogleHomePage(driver)
                .openPage()
                .searchForTerm()
                .clickSearchResult();

        WebElement totalPrice = calculatorPage
                .clickComputeEngineTab()
                .fillInComputeEngineForm()
                .getProvisioningModel();

        double totalPriceValue = 1;//totalPrice.getText();

        Assert.assertTrue(totalPriceValue == TOTAL_PRICE, "Local SSD is not equal");
    }
*/
    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
