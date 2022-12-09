package com.epam.webdriver.aleksandr_gostev.testDataParams;

import org.testng.annotations.DataProvider;

public class ParametersByDataProvider {

    @DataProvider(name = "PageFieldsValues")
    public static Object[][] getDataFromDataprovider() {
        return new Object[][] {
                { 4, "", "free", "Spot", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-64", true, "NVIDIA_TESLA_P100", "1", "3x375", "europe-west3"}
        };
    }
}
