package com.epam.webdriver.aleksandr_gostev.testDataParams;

import org.testng.annotations.DataProvider;

public class ParametersByDataProvider {
    @DataProvider(name = "PageFieldsValues")
    public static Object[][] getDataFromDataprovider() {
        return new Object[][]{
                {"git config --global user.name  \"New Sheriff in Town\"" +
                        "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" +
                        "git push origin master --force",
                        "Bash",
                        "10 Minutes",
                        "how to gain dominance among developers"}
        };
    }
}
