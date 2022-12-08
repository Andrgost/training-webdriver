package com.epam.webdriver.aleksandr_gostev.testDataParams;

import org.testng.annotations.DataProvider;

public class ParametersByDataProvider {

    @DataProvider(name = "PageFieldsValues")
    public static Object[][] getDataFromDataprovider() {
        return new Object[][] {
                { "Spot", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-64", "3x375", "europe-west3"}
        };
    }
}
