package DataProviders;

import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.ExcelHelper;
import ai.planz.dev.helpers.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderFactory {
    //Pass multiple sets of parameters in a single run (all rows and columns in the Excel file)
    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + "src/test/resources/PlanZTestData/LoginPage.xlsx", "NormalLogin");
        WriteLogs.info("Login Data from Excel: " + data);
        return data;
    }
//Config Start row and end row
    @DataProvider(name = "data_provider_login_excel_hashtable")
    public Object[][] dataLoginFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/PlanZTestData/LoginPage.xlsx", "NormalLogin", 1, 3);
        WriteLogs.info("Login Data from Excel: " + data);
        return data;
    }
}
