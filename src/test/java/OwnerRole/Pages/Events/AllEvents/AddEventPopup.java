package OwnerRole.Pages.Events.AllEvents;

import Common.Actions;
import Common.CommonVerifications;
import Common.LeftMenu_Header_Footer;
import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.Logs.WriteLogs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;

import java.util.List;

public class AddEventPopup {
    private final WebDriver driver;
    CommonVerifications CommonVerifications = new CommonVerifications();
    LeftMenu_Header_Footer LeftMenu_Header_Footer = new LeftMenu_Header_Footer();

    //Declare the constructor function to pass the driver from the outside (General function) into this class for use.
    public AddEventPopup() {
        this.driver = (BrowserManager.getDriver());
    }

    //Locate elements
    private By weddingPartyShortcode = By.xpath("//label[contains(text(),'Đám cưới')]");
    private By tryMeButton = By.xpath("//button[@id='try-me']");
    //Locate the latest conversation
    private By latestMessage = By.xpath("(//div[@id='box-instruction'])[last()]");

    private By estimateButton = By.xpath("//button[@id='question-submit']");
    //Locate tables' elements in Task estimation screen
    private By labelBeforeEventDay = By.xpath("(//div[@class='question list-tasks'])[1]//div[contains(@class, 'task-labels')]");
    private By labelOnEventDay = By.xpath("(//div[@class='question list-tasks'])[2]//div[contains(@class, 'task-labels')]");
    private By labelAfterEventDay = By.xpath("(//div[@class='question list-tasks'])[3]//div[contains(@class, 'task-labels')]");

    private By createButton = By.xpath("//a[@class='btn btn-start-event']");

    //Actions


    //add Wedding Party Shortcode, given at Add Event Popup
    public void addWeddingPartyShortcode() {
        Actions.clickElement(weddingPartyShortcode);
    }

    //Get text entered in the text area
    public String getTextareaContentByName(String name) {
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        return (String) js.executeScript(
                "const el = document.querySelector('textarea[name=\"" + name + "\"]'); return el ? el.value : null;"
        );
    }

    // Check if text entered in the text area is correct
    public void checkTextareaContentByName(String name, String expectedText) {
        String actualText = getTextareaContentByName(name);
        Assert.assertEquals(actualText, expectedText, "The text entered in the text area is not correct.");
        WriteLogs.info("The text entered in the text area is correct: " + actualText);
    }

    //Click on Try Me button
    public void clickTryMeButton() {
        Actions.clickElement(tryMeButton);
    }

    //Check if more suggestions to create a Wedding Party appear
    public void weddingPartySuggestionsAvailable() {
// Get text from latest message
        String latestText = Actions.getElementText(latestMessage);

// Log it
        WriteLogs.info("📥 Latest message content: " + latestText);

// Check if it contains a specific keyword (Đám cưới) but lowercase
        Assert.assertTrue(latestText.contains("đám cưới"), "Expected text to contain 'đám cưới'");
        WriteLogs.info("✅ The latest message contains 'đám cưới' as expected.");
    }
// Answer the suggested questions (in case first options are selected)
public void answerSuggestedQuestions() {
    List<WebElement> questions = BrowserManager.getDriver().findElements(By.xpath("//div[@class='box-question']"));

    for (WebElement question : questions) {
        List<WebElement> choices = question.findElements(By.xpath(".//label[contains(@class, 'result-item')]"));

        for (WebElement choice : choices) {
            if (choice.isDisplayed()) {
                Actions.clickElement(choice); // your reusable click method
                break; // only click the first visible one
            }
        }
    }
}

    //Click Estimate button
    public void clickEstimateButton() {
        Actions.clickElement(estimateButton);
        WriteLogs.info("Clicked on Estimate button.");
    }
//Check if Task estimation screen is displayed:
    public void checkTaskEstimationScreenDisplayed() {
        Actions.isElementDisplayed(labelBeforeEventDay);
        Actions.isElementDisplayed(labelOnEventDay);
        Actions.isElementDisplayed(labelAfterEventDay);
        WriteLogs.info("Task estimation screen is displayed with all tables: Before the event day, On the event day and After the event day");
    }
//Create event
public void clickCreateButton() {
        Actions.clickElement(createButton);
        WriteLogs.info("Clicked on Create button to create the event.");    }

    //Double check if the event is created successfully
    public void confirmEventCreated(ITestContext context) {
        //Display the total records before creating the event
        int previousTotalRecords = (int) context.getAttribute("previousTotalRecords");
        WriteLogs.info("Total Records: " + previousTotalRecords);
        //Access Events overview page
        LeftMenu_Header_Footer.clickEventsTab();
        //Extract the total records after creating the event
        CommonVerifications.checkTotalRecordsIncreasedByOne(previousTotalRecords);
    }
}
