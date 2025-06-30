package OwnerRole.Testcases.Events.Tasks.TaskList.TaskDetail;

import Common.LeftMenu_Header_Footer;
import Login.Testcases.T1_NormalLogin;
import OwnerRole.Pages.Events.AllEvents.AddEventPopup;
import OwnerRole.Testcases.Events.AllEvents.T2_AddEventPopup;
import OwnerRole.Testcases.Events.AllEvents.T1_AllEvents;
import ai.planz.dev.Browsers.TestSetup;
import ai.planz.dev.helpers.ExcelHelper;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T1_BudgetAndExpense extends TestSetup {

    private ExcelHelper excelHelper;
    private T1_NormalLogin T1_NormalLogin;
    private LeftMenu_Header_Footer LeftMenu_Header_Footer;
    T1_AllEvents T1_AllEvents = new T1_AllEvents();
    AddEventPopup AddEventPopup = new AddEventPopup();
    T2_AddEventPopup T1_AddEventPopup = new T2_AddEventPopup();

    @BeforeClass
    public void setup() {
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/PlanZTestData/LoginPage.xlsx", "NormalLogin");

        T1_NormalLogin = new T1_NormalLogin(excelHelper); // ✅ pass it here
        LeftMenu_Header_Footer = new LeftMenu_Header_Footer();
    }

    //Preconditions
    @Test(priority = 1)
    public void loginSuccess() {
        T1_NormalLogin.loginSuccess();  // ✅ will now have access to ExcelHelper
    }

    @Test(priority = 2)
    public void selectVietnamese() {
        LeftMenu_Header_Footer.selectVietnamese();
    }

    //Add new event
    @Test(priority = 3)
    public void openAddEventPopup(ITestContext context) {
        T1_AllEvents.openAddEventPopup(context);
    }

    @Test(priority = 4)
    public void createWeddingPartyUsingSuggestions() {
            T1_AddEventPopup.createWeddingPartyUsingSuggestions();
    }
    @Test(priority = 5)
    public void confirmEventCreated(ITestContext context) {
        T1_AddEventPopup.confirmEventCreated(context);
    }
}
