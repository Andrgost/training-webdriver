package com.epam.webdriver.aleksandrgostev.pages.googlecloud;

import com.epam.webdriver.aleksandrgostev.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.String.format;

public class GoogleCloudPricingCalculatorPage extends BasePage {

    // Frames
    protected static final By COMPUTE_ENGINE_FRAME = By.id("myFrame");
    protected static final By MAIN_FRAME = By.xpath("//*[@id='cloud-site']//iframe");

    // Elements
    private static final String GPU_TYPE_XPATH_PATTERN = "//md-option[@value='%s']";
    private static final String OS_XPATH_PATTERN = "//md-option[@value='%s']/div[@class='md-text']";
    private static final String NUMBER_OF_GPU_XPATH_PATTERN = "//md-option[contains(@ng-repeat,'gpuType') and @value='%s']";
    private static final String PROVISIONING_MODEL_XPATH_PATTERN = "//md-select-menu/md-content/md-option/div[contains(text(), '%s')]";
    private static final String LOCAL_SSD_XPATH_PATTERN = "//md-option[contains(@ng-repeat, 'dynamicSsd')]/div[contains(text(), '%s')]";
    private static final String DATACENTER_LOCATION_XPATH_PATTERN = "//md-option[contains(@ng-repeat,'computeServer') and @value='%s']/div";

    @FindBy(xpath = "//md-pagination-wrapper/descendant::div[@title='Compute Engine']")
    private WebElement computeEngineTab;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operatingSystemSoftwareSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.class']")
    private WebElement provisioningModelSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    private WebElement machineTypeSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement gpuCheckbox;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuTypeSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement numberOfGPUSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSSDSelect;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationSelect;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculatorPage clickComputeEngineTab() {
        driver.switchTo().frame(driver.findElement(MAIN_FRAME));
        driver.switchTo().frame(driver.findElement(COMPUTE_ENGINE_FRAME));
        computeEngineTab.click();
        return this;
    }

    public EstimatePage fillInComputeEngineForm(Integer numberOfInstances,
                                                String operatingSystemSoftware,
                                                String provisioningModel,
                                                String machineType,
                                                String gpuType,
                                                String numberOfGPU,
                                                String localSSD,
                                                String datacenterLocation) {
        //Instances input
        numberOfInstancesInput.sendKeys(numberOfInstances.toString());

        //Operating System / Software dropdown
        operatingSystemSoftwareSelect.click();
        driver.findElement(By.xpath(format(OS_XPATH_PATTERN, operatingSystemSoftware))).click();

        //Provisioning model (VM Class) dropdown
        provisioningModelSelect.click();
        waitAndClick(driver.findElement(By.xpath(format(PROVISIONING_MODEL_XPATH_PATTERN, provisioningModel))));

        //Machine type (Instance type) dropdown
        waitAndClick(machineTypeSelect);
        waitAndClick(driver.findElement(By.xpath(format(GPU_TYPE_XPATH_PATTERN, machineType))));

        //Add GPUs checkbox
        waitAndClick(gpuCheckbox);

        //GPU type dropdown
        gpuTypeSelect.click();
        waitAndClick(driver.findElement(By.xpath(format(GPU_TYPE_XPATH_PATTERN, gpuType))));

        //Number of GPUs dropdown
        numberOfGPUSelect.click();
        waitAndClick(driver.findElement(By.xpath(format(NUMBER_OF_GPU_XPATH_PATTERN, numberOfGPU))));

        //Local SSD dropdown
        localSSDSelect.click();
        waitAndClick(driver.findElement(By.xpath(format(LOCAL_SSD_XPATH_PATTERN, localSSD))));

        //Datacenter Location dropdown
        datacenterLocationSelect.click();
        waitAndClick(driver.findElement(By.xpath(format(DATACENTER_LOCATION_XPATH_PATTERN, datacenterLocation))));

        addToEstimateButton.click();

        return new EstimatePage(driver);
    }
}
