package Common;

import org.openqa.selenium.By;

public class LeftMenu_Header_Footer {
    public By eventsTab = By.xpath("//a[contains(@href, 'projects/all_projects')]");
    public By languageSelection = By.xpath("//a[@id='personal-language-icon']");
    public By englishOption = By.xpath("//a[contains(@data-action-url,'save_personal_language/English')]");
    public By englishChecked = By.xpath("//a[.//strong[text()='English'] and .//span[contains(@class, 'checkbox-checked')]]");
    public By vietnameseOption = By.xpath("//a[contains(@data-action-url,'save_personal_language/Vietnamese')]");
    public By vietnameseChecked = By.xpath("//a[.//strong[text()='Vietnamese'] and .//span[contains(@class, 'checkbox-checked')]]");

    public void selectVietnamese() {
    Actions.clickElement(languageSelection);
    Actions.clickElement(vietnameseOption);
    Actions.isElementDisplayed(vietnameseChecked);
    }

    public void clickEventsTab() {
        Actions.clickElement(eventsTab);
    }
}
