package com.epam.webdriver.aleksandr_gostev.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPricingCalculatorPage {

    private final WebDriver driver;

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

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement GPUCheckbox;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement GPUTypeSelect;
    private WebElement GPUTypeOption;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement numberOfGPUSelect;
    private WebElement numberOfGPUOption;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSSDSelect;
    private WebElement localSSDOption;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationSelect;
    private WebElement datacenterLocationOption;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsageSelect;
    private WebElement committedUsageOption;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

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

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPricingCalculatorPage clickComputeEngineTab() {
        WebElement mainFrame = driver.findElement(By.xpath("//*[@id='cloud-site']//iframe"));
        driver.switchTo().frame(mainFrame);
        WebElement computeEngineFrame = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(computeEngineFrame);
        System.out.println("Driver switched to the computeEngineFrame");
        computeEngineTab.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage fillInComputeEngineForm() {
        //Instances input
        numberOfInstancesInput.sendKeys("4");

        //What Are These Instances For input
        whatAreTheseInstancesForInput.sendKeys("");

        //Operating System / Software dropdown
        operatingSystemSoftwareSelect.click();
        operatingSystemSoftwareOption = driver.findElement(By.xpath("//md-option[@value='free']"));
        operatingSystemSoftwareOption.click();

        //Provisioning model (VM Class) dropdown
        provisioningModelSelect.click();
        provisioningModelOption = driver.findElement(By.xpath("//md-select-menu/md-content/md-option/div[contains(text(),'Spot')]/parent::*"));
        provisioningModelOption.click();

        //Machine type (Instance type) dropdown
        machineTypeSelect.click();
        machineTypeOption = driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']"));
        machineTypeOption.click();

        //Add GPUs checkbox
        GPUCheckbox.click();

        //GPU type dropdown
        GPUTypeSelect.click();
        GPUTypeOption = driver.findElement(By.xpath("//md-option[@value='NVIDIA_TESLA_P100']"));
        GPUTypeOption.click();

        //Number of GPUs dropdown
        numberOfGPUSelect.click();
        numberOfGPUOption = driver.findElement(By.xpath("//md-option[contains(@ng-repeat,'gpuType') and @value='1']"));
        numberOfGPUOption.click();

        //Local SSD dropdown
        localSSDSelect.click();
        localSSDOption = driver.findElement(By.xpath("//md-option[contains(@ng-repeat,'dynamicSsd') and @value='2']"));
        localSSDOption.click();

        //Datacenter Location dropdown
        datacenterLocationSelect.click();
        datacenterLocationOption = driver.findElement(By.xpath("//md-option[contains(@ng-repeat,'computeServer') and @value='europe-west3']"));
        datacenterLocationOption.click();

        //Committed usage
        committedUsageSelect.click();
        committedUsageOption = driver.findElement(By.xpath("//md-option[@value='1']"));
        committedUsageOption.click();

        addToEstimateButton.click();

        return this;
    }

    private void clickElementViaJS(JavascriptExecutor executor, Object element) {
        executor.executeScript("arguments[0].click();", element);
    }
}
