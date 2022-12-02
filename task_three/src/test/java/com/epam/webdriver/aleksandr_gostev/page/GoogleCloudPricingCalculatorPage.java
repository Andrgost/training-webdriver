package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleCloudPricingCalculatorPage {

    private WebDriver driver;

    @FindBy(xpath = "//md-pagination-wrapper/descendant::div[@title='Compute Engine']")
    private WebElement computeEngineTab;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.label']")
    private WebElement whatAreTheseInstancesForInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operatingSystemSoftwareSelect;
    private WebElement operatingSystemSoftwareOption;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.class']")
//"//md-select[@ng-model='listingCtrl.computeServer.class']")
    private WebElement provisioningModelSelect;
    private WebElement provisioningModelOption;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    private WebElement machineTypeSelect;
    private WebElement machineTypeOption;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement GPUCheckbox;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement GPUTypeSelect;
    private WebElement GPUTypeOption;

    @FindBy(xpath = "md-select[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement numberOfGPUSelect;
    private WebElement numberOfGPUOption;

    @FindBy(xpath = "md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSSDSelect;
    private WebElement localSSDOption;

    @FindBy(xpath = "md-select[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationSelect;
    private WebElement datacenterLocationOption;

    @FindBy(xpath = "md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsageSelect;
    private WebElement committedUsageOption;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getProvisioningModel() {
        /*
        provisioningModelSelect.click();
        provisioningModelOption = driver.findElement(By.id("select_option_1305"));
        */
        return provisioningModelOption;
    }

    public WebElement getMachineType() {
        /*
        provisioningModelSelect.click();
        provisioningModelOption = driver.findElement(By.id("select_option_1305"));
        */
        return machineTypeOption;
    }

    public WebElement getDatacenterLocation() {
        /*
        provisioningModelSelect.click();
        provisioningModelOption = driver.findElement(By.id("select_option_1305"));
        */
        return datacenterLocationOption;
    }

    public WebElement getLocalSSD() {
        /*
        provisioningModelSelect.click();
        provisioningModelOption = driver.findElement(By.id("select_option_1305"));
        */
        return localSSDOption;
    }

    public GoogleCloudPricingCalculatorPage clickComputeEngineTab() {
        driver.switchTo().frame(0);
        driver.switchTo().frame("myFrame");
        System.out.println("frame is switched 1");
        computeEngineTab.click();

        return this;
    }

    public GoogleCloudPricingCalculatorPage fillInComputeEngineForm() {
        //Instances input
        numberOfInstancesInput.sendKeys("4");

        //What Are These Instances For input
        whatAreTheseInstancesForInput.sendKeys("");

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        //Operating System / Software dropdown
        clickElementViaJS(executor, operatingSystemSoftwareSelect);
        operatingSystemSoftwareOption = driver.findElement(By.xpath("//md-option[@value='free']"));
        clickElementViaJS(executor, operatingSystemSoftwareOption);

        //Provisioning model (VM Class) dropdown
        clickElementViaJS(executor, provisioningModelSelect);
        provisioningModelOption = driver.findElement(By.xpath("//md-option[@value='preemptible']"));
        clickElementViaJS(executor, provisioningModelOption);

        //Machine type (Instance type) dropdown
        clickElementViaJS(executor, machineTypeSelect);
        machineTypeOption = driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']"));
        clickElementViaJS(executor, machineTypeOption);
/*
        //Add GPUs checkbox
        //new WebDriverWait(driver, Duration.ofSeconds(10))
        //        .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")));
        clickElementViaJS(executor, GPUCheckbox);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("select_option_1305")));

        //GPU type dropdown
        clickElementViaJS(executor, GPUTypeSelect);
        GPUTypeOption = driver.findElement(By.xpath("//md-option[@value='NVIDIA_TESLA_P100']"));
        clickElementViaJS(executor, GPUTypeOption);

        //Number of GPUs dropdown
        clickElementViaJS(executor, numberOfGPUSelect);
        numberOfGPUOption = driver.findElement(By.xpath("//md-option[@value='1']"));
        clickElementViaJS(executor, numberOfGPUOption);

        //Local SSD dropdown
        clickElementViaJS(executor, localSSDSelect);
        localSSDOption = driver.findElement(By.xpath("//md-option[@value='2']"));
        clickElementViaJS(executor, localSSDOption);

        //Datacenter Location dropdown
        clickElementViaJS(executor, datacenterLocationSelect);
        datacenterLocationOption = driver.findElement(By.xpath("//md-option[@value='europe-west3']"));
        clickElementViaJS(executor, datacenterLocationOption);
*/
        //Commited usage
        /*
        clickElementViaJS(executor, committedUsageSelect);
        committedUsageOption = driver.findElement(By.xpath("//md-option[@value='1']"));
        clickElementViaJS(executor, committedUsageOption);
        */

        clickElementViaJS(executor, addToEstimateButton);

        return this;
    }

    private void clickElementViaJS(JavascriptExecutor executor, Object element) {
        executor.executeScript("arguments[0].click();", element);
    }
}
