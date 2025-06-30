package OwnerRole.Testcases.Events.AllEvents;

import Common.CommonVerifications;
import Common.LeftMenu_Header_Footer;
import Login.Testcases.T1_NormalLogin;
import OwnerRole.Pages.Events.AllEvents.AllEvents;
import ai.planz.dev.Browsers.TestSetup;
import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.ExcelHelper;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T1_AllEvents extends TestSetup {
    private ExcelHelper excelHelper;
    private T1_NormalLogin T1_NormalLogin;
    AllEvents AllEvents = new AllEvents();
    CommonVerifications CommonVerifications = new CommonVerifications();
    LeftMenu_Header_Footer LeftMenu_Header_Footer = new LeftMenu_Header_Footer();
    @BeforeClass
    public void setup() {
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/PlanZTestData/LoginPage.xlsx", "NormalLogin");

//        T1_NormalLogin = new T1_NormalLogin(excelHelper); // ✅ pass it here
//        LeftMenu_Header_Footer = new LeftMenu_Header_Footer();
    }
    @Test(priority = 1)
    public void openAddEventPopup(ITestContext context) {
        LeftMenu_Header_Footer.clickEventsTab();
        int totalPreviousRecords = CommonVerifications.extractTotalRecords(); // Extract the total records before opening the popup to create new event
// Store it to Retrieve in another class
        context.setAttribute("previousTotalRecords", totalPreviousRecords);
        AllEvents.openAddEventPopup();
    }
}
