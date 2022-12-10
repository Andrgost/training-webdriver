package com.epam.webdriver.aleksandr_gostev.page.google_cloud;

import com.epam.webdriver.aleksandr_gostev.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPricingCalculatorPage extends BasePage {

    @FindBy(xpath = "//md-pagination-wrapper/descendant::div[@title='Compute Engine']")
    private WebElement computeEngineTab;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.label']")
    private WebElement whatAreTheseInstancesForInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operatingSystemSoftwareSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.class']")
    private WebElement provisioningModelSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    private WebElement machineTypeSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement GPUCheckbox;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement GPUTypeSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement numberOfGPUSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSSDSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsageSelect;
    private WebElement committedUsageOption;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculatorPage clickComputeEngineTab() {
        WebElement mainFrame = driver.findElement(By.xpath("//*[@id='cloud-site']//iframe"));
        driver.switchTo().frame(mainFrame);

        WebElement computeEngineFrame = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(computeEngineFrame);

        computeEngineTab.click();
        return this;
    }

    public EstimatePage fillInComputeEngineForm(Integer numberOfInstances,
                                                String whatAreTheseInstancesFor,
                                                String operatingSystemSoftware,
                                                String provisioningModel,
                                                String machineType,
                                                Boolean gpu,
                                                String gpuType,
                                                String numberOfGPU,
                                                String localSSD,
                                                String datacenterLocation) {
        //Instances input
        numberOfInstancesInput.sendKeys(numberOfInstances.toString());

        //What Are These Instances For input
        whatAreTheseInstancesForInput.sendKeys(whatAreTheseInstancesFor);

        //Operating System / Software dropdown
        operatingSystemSoftwareSelect.click();
        WebElement operatingSystemSoftwareOption = driver.findElement(By.xpath(String.format("//md-option[@value='%s']/div[@class='md-text']", operatingSystemSoftware)));
        operatingSystemSoftwareOption.click();

        //Provisioning model (VM Class) dropdown
        provisioningModelSelect.click();
        WebElement provisioningModelOption = driver.findElement(By.xpath(String.format("//md-select-menu/md-content/md-option/div[contains(text(), '%s')]", provisioningModel)));
        provisioningModelOption.click();

        //Machine type (Instance type) dropdown
        machineTypeSelect.click();
        WebElement machineTypeOption = driver.findElement(By.xpath(String.format("//md-option[@value='%s']", machineType)));
        machineTypeOption.click();

        //Add GPUs checkbox
        if (gpu) GPUCheckbox.click();

        //GPU type dropdown
        GPUTypeSelect.click();
        WebElement GPUTypeOption = driver.findElement(By.xpath(String.format("//md-option[@value='%s']", gpuType)));
        GPUTypeOption.click();

        //Number of GPUs dropdown
        numberOfGPUSelect.click();
        WebElement numberOfGPUOption = driver.findElement(By.xpath(String.format("//md-option[contains(@ng-repeat,'gpuType') and @value='%s']", numberOfGPU)));
        numberOfGPUOption.click();

        //Local SSD dropdown
        localSSDSelect.click();
        WebElement localSSDOption = driver.findElement(By.xpath(String.format("//md-option[contains(@ng-repeat, 'dynamicSsd')]/div[contains(text(), '%s')]", localSSD)));
        localSSDOption.click();

        //Datacenter Location dropdown
        datacenterLocationSelect.click();
        WebElement datacenterLocationOption = driver.findElement(By.xpath(String.format("//md-option[contains(@ng-repeat,'computeServer') and @value='%s']/div", datacenterLocation)));
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
