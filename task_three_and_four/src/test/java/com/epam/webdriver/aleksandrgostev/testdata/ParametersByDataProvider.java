package com.epam.webdriver.aleksandrgostev.testdata;

import org.testng.annotations.DataProvider;

public class ParametersByDataProvider {

    @DataProvider(name = "PageFieldsValues")
    public static Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {4, "", "free", "Spot", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-64", true, "NVIDIA_TESLA_P100", "1", "3x375", "europe-west3",
                        "Spot", "n1-standard-8", "2x375", "Salt Lake City", 2079.78D}
        };
    }
}
