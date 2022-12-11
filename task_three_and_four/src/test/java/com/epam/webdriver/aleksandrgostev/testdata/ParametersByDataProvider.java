package com.epam.webdriver.aleksandrgostev.testdata;

import org.testng.annotations.DataProvider;

public class ParametersByDataProvider {

    @DataProvider(name = "fieldValuesDataProvider")
    public static Object[][] fieldValuesDataProvider() {
        return new Object[][]{
                {"Google Cloud Platform Pricing Calculator", 4, "free", "Spot", "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8",
                        "NVIDIA_TESLA_P100", "1", "3x375", "europe-west3", "Frankfurt", "n1-standard-8", "1,745.12"}
        };
    }
}
