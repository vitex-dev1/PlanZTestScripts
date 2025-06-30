//In this class, we will define common verifications that will likely be reused across many tests

 package Common;
import ai.planz.dev.Logs.WriteLogs;
import org.testng.Assert;

import org.openqa.selenium.By;

public class CommonVerifications {
    public By eventsTab = By.xpath("//a[contains(@href, 'projects/all_projects')]");

    //Count the number of records in page
    public int extractTotalRecords() {
        // Get the text content of the element containing the total record information
        String infoText = Actions.getElementText(By.xpath("//div[@id='project-table_info']")); // Example: "1-4 / 4"

        // Extract the total records using string manipulation or regex
        String totalRecordsText = infoText.split("/")[1].trim();
        //Convert from String to Integer
        int totalRecords = Integer.parseInt(totalRecordsText);

        // Log the total records
        WriteLogs.info("Total Records: " + totalRecords);
        return totalRecords;
    }

    //Check that the number of records has increased by 1.
    public void checkTotalRecordsIncreasedByOne(int previousTotalRecords) {
        int currentTotalRecords = previousTotalRecords + 1;
        extractTotalRecords();
        Assert.assertEquals(currentTotalRecords, previousTotalRecords + 1, "The total number of records did not increase by 1.");
        WriteLogs.info("The total number of records has increased by 1 as expected.");
    }


}
