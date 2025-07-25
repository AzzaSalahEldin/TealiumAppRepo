package utils;
import org.testng.annotations.DataProvider;


public class CSVDataProviders {
    @DataProvider(name = "registrationData")
    public static Object[][] registrationData() throws Exception {
        return CSVUtils.readCSV("Register.csv");
    }

    @DataProvider(name = "loginData")
    public static Object[][] loginData() throws Exception {
        return CSVUtils.readCSV("Login.csv");
    }
}
