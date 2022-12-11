package com.epam.webdriver.aleksandrgostev.page.googlecloud;

import com.epam.webdriver.aleksandrgostev.page.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.String.format;

public class GoogleCloudPricingCalculatorPage extends BasePage {

    private static final String DATACENTER_LOCATION_XPATH_PATTERN = "//md-option[contains(@ng-repeat,'computeServer') and @value='%s']/div";
    private static final String LOCAL_SSD_XPATH_PATTERN = "//md-option[contains(@ng-repeat, 'dynamicSsd')]/div[contains(text(), '%s')]";
    private static final String NUMBER_OF_GPU_XPATH_PATTERN = "//md-option[contains(@ng-repeat,'gpuType') and @value='%s']";
    private static final String GPU_TYPE_XPATH_PATTERN = "//md-option[@value='%s']";

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
        WebElement mainFrame = driver.findElement(By.xpath("//*[@id='cloud-site']//iframe"));
        driver.switchTo().frame(mainFrame);

        WebElement computeEngineFrame = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(computeEngineFrame);

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
        WebElement operatingSystemSoftwareOption = driver.findElement(By.xpath(format("//md-option[@value='%s']/div[@class='md-text']", operatingSystemSoftware)));
        operatingSystemSoftwareOption.click();

        //Provisioning model (VM Class) dropdown
        provisioningModelSelect.click();
        WebElement provisioningModelOption = driver.findElement(By.xpath(format("//md-select-menu/md-content/md-option/div[contains(text(), '%s')]", provisioningModel)));
        provisioningModelOption.click();

        //Machine type (Instance type) dropdown
        machineTypeSelect.click();
        WebElement machineTypeOption = driver.findElement(By.xpath(format(GPU_TYPE_XPATH_PATTERN, machineType)));
        waitForVisibility(machineTypeOption);
        machineTypeOption.click();

        //Add GPUs checkbox
        waitForVisibility(gpuCheckbox);
        gpuCheckbox.click();

        //GPU type dropdown
        gpuTypeSelect.click();
        WebElement GPUTypeOption = driver.findElement(By.xpath(format(GPU_TYPE_XPATH_PATTERN, gpuType)));
        waitForVisibility(GPUTypeOption);
        GPUTypeOption.click();

        //Number of GPUs dropdown
        numberOfGPUSelect.click();
        WebElement numberOfGPUOption = driver.findElement(By.xpath(format(NUMBER_OF_GPU_XPATH_PATTERN, numberOfGPU)));
        numberOfGPUOption.click();

        //Local SSD dropdown
        localSSDSelect.click();
        WebElement localSsdOption = driver.findElement(By.xpath(format(LOCAL_SSD_XPATH_PATTERN, localSSD)));
        waitForVisibility(localSsdOption);
        localSsdOption.click();

        //Datacenter Location dropdown
        datacenterLocationSelect.click();
        WebElement datacenterLocationOption = driver.findElement(By.xpath(format(DATACENTER_LOCATION_XPATH_PATTERN, datacenterLocation)));
        waitForVisibility(datacenterLocationOption);
        datacenterLocationOption.click();

        addToEstimateButton.click();

        return new EstimatePage(driver);
    }
}
