package OwnerRole.Testcases.Events.AllEvents;

import Common.LeftMenu_Header_Footer;
import Login.Testcases.T1_NormalLogin;
import OwnerRole.Pages.Events.AllEvents.AddEventPopup;
import OwnerRole.Pages.Events.AllEvents.AllEvents;
import ai.planz.dev.Browsers.TestSetup;
import ai.planz.dev.helpers.ExcelHelper;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T2_AddEventPopup extends TestSetup {
    private ExcelHelper excelHelper;
    private T1_NormalLogin T1_NormalLogin;
    private LeftMenu_Header_Footer LeftMenu_Header_Footer;
    AllEvents AllEvents = new AllEvents();
    AddEventPopup AddEventPopup = new AddEventPopup();

    @BeforeClass
    public void setup() {
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/PlanZTestData/LoginPage.xlsx", "NormalLogin");

        T1_NormalLogin = new T1_NormalLogin(excelHelper); // ✅ pass it here
        LeftMenu_Header_Footer = new LeftMenu_Header_Footer();
    }
    @Test(priority = 1)
    public void createWeddingPartyUsingSuggestions() {
        AddEventPopup.addWeddingPartyShortcode();
        AddEventPopup.getTextareaContentByName("message")  ;
        AddEventPopup.checkTextareaContentByName("message","Đám cưới")  ;
        AddEventPopup.clickTryMeButton();
        AddEventPopup.weddingPartySuggestionsAvailable();
        AddEventPopup.answerSuggestedQuestions();
        AddEventPopup.clickEstimateButton();
        AddEventPopup.checkTaskEstimationScreenDisplayed();
        AddEventPopup.clickCreateButton();
    }
    @Test(priority = 2)
    public void confirmEventCreated(ITestContext context){
        AddEventPopup.confirmEventCreated(context);
    }
}
