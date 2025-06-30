package OwnerRole.Pages.Events.AllEvents;

import Common.Actions;
import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.Logs.WriteLogs;
import org.openqa.selenium.*;

public class AllEvents {
    private final WebDriver driver;

    //Declare the constructor function to pass the driver from the outside (General function) into this class for use.
    public AllEvents() {
        this.driver = (BrowserManager.getDriver());
    }

    //Locate elements
    private By addEventButton = By.xpath("//div/a[@title='Thêm sự kiện']");

    //Actions
    public void openAddEventPopup() {
        Actions.clickElement(addEventButton);
        Actions.isElementDisplayed(By.xpath("//div[@class='modal-content']//h4[contains(text(),'Thêm sự kiện')]"));
        WriteLogs.info("Add Event Popup is displayed");
    }

}