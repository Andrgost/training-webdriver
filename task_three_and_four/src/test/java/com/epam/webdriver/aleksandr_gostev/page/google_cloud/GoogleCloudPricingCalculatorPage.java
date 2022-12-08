package com.epam.webdriver.aleksandr_gostev.page.google_cloud;

import org.openqa.selenium.By;
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

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.class']")
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

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPricingCalculatorPage clickComputeEngineTab() {
        WebElement mainFrame = driver.findElement(By.xpath("//*[@id='cloud-site']//iframe"));
        driver.switchTo().frame(mainFrame);

        WebElement computeEngineFrame = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(computeEngineFrame);

        computeEngineTab.click();
        return this;
    }

    public EstimatePage fillInComputeEngineForm(String provisioningModel,
                                                String machineType,
                                                String localSSD,
                                                String datacenterLocation) {
        //Instances input
        numberOfInstancesInput.sendKeys("4");

        //What Are These Instances For input
        whatAreTheseInstancesForInput.sendKeys("");

        //Operating System / Software dropdown
        operatingSystemSoftwareSelect.click();
        operatingSystemSoftwareOption = driver.findElement(By.xpath("//md-option[@value='free']/div[@class='md-text']"));
        operatingSystemSoftwareOption.click();

        //Provisioning model (VM Class) dropdown
        provisioningModelSelect.click();
        provisioningModelOption = driver.findElement(By.xpath(String.format("//md-select-menu/md-content/md-option/div[contains(text(), '%s')]", provisioningModel)));
        provisioningModelOption.click();

        //Machine type (Instance type) dropdown
        machineTypeSelect.click();
        machineTypeOption = driver.findElement(By.xpath(String.format("//md-option[@value='%s']", machineType)));
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
        localSSDOption = driver.findElement(By.xpath(String.format("//md-option[contains(@ng-repeat, 'dynamicSsd')]/div[contains(text(), '%s')]", localSSD)));
        localSSDOption.click();

        //Datacenter Location dropdown
        datacenterLocationSelect.click();
        datacenterLocationOption = driver.findElement(By.xpath(String.format("//md-option[contains(@ng-repeat,'computeServer') and @value='%s']", datacenterLocation)));
        datacenterLocationOption.click();

        //Committed usage - depends on Provisioning model = Regular
        /*
        committedUsageSelect.click();
        committedUsageOption = driver.findElement(By.xpath("//md-option[@value='1']"));
        committedUsageSelect.click();
        */

        addToEstimateButton.click();

        return new EstimatePage(driver);
    }
}
