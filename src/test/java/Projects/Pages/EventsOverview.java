package Projects.Pages;

import Common.Actions;
import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.PropertiesHelper;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;

public class EventsOverview {
    private final WebDriver driver;

    //Khai báo hàm xây dựng, để truyền driver từ bên ngoài (Ham chung) vào chính class này sử dụng
    public EventsOverview() {
        this.driver = (BrowserManager.getDriver());
    }

    //Locate elements
    private By customersMenu = By.xpath("//a[@href='http://dev3.vitex.asia:8009/en/admin/clients' and .//i[@class='fa fa-building-o']]");
    private By pageTitle = By.xpath("//li[normalize-space()='1 '?/.']");
    private By newCustomer = By.xpath("//a[contains(@class, 'datatable-redirect') and contains(@class, 'btn-secondary')]");
    private By copyTableButton = By.xpath("//i[@title='Copy']");
    private By exportDropdown = By.xpath("//button[(normalize-space(@class)='btn btn-default buttons-collection btn-secondary')]");
    private By printButton = By.xpath("//button[(normalize-space(@class)='btn btn-default buttons-print btn-secondary')]");
    private By colvisDropdown = By.xpath("//button[contains(@class, 'buttons-collection buttons-colvis')]");
    public By colvisID = By.xpath("//li[@data-cv-idx='0']");
    public By colvisIDVisibility = By.xpath("//li[(@data-cv-idx='0') and contains(@class, 'active')]");
    public By colvisClientName = By.xpath("//li[@data-cv-idx='1']");
    private By colvisClientNameVisibility = By.xpath("//li[(@data-cv-idx='1') and contains(@class, 'active')]");
    public By colvisAddress = By.xpath("//li[@data-cv-idx='2']");
    public By colvisAddressVisibility = By.xpath("//li[(@data-cv-idx='2') and contains(@class, 'active')]");
    private By colvisCountry = By.xpath("//li[@data-cv-idx='3']");
    private By colvisCountryVisibility = By.xpath("//li[(@data-cv-idx='3') and contains(@class, 'active')]");
    private By colvisPMS = By.xpath("//li[@data-cv-idx='4']");
    private By colvisPMSVisibility = By.xpath("//li[(@data-cv-idx='4') and contains(@class, 'active')]");
    private By colvisCategory = By.xpath("//li[@data-cv-idx='5']");
    private By colvisCategoryVisibility = By.xpath("//li[(@data-cv-idx='5') and contains(@class, 'active')]");
    private By colvisDemo = By.xpath("//li[@data-cv-idx='6']");
    private By colvisDemoVisibility = By.xpath("//li[(@data-cv-idx='6') and contains(@class, 'active')]");
    private By colvisREF = By.xpath("//li[@data-cv-idx='7']");
    private By colvisREFVisibility = By.xpath("//li[(@data-cv-idx='7') and contains(@class, 'active')]");
    private By colvisInstallationID = By.xpath("//li[@data-cv-idx='8']");
    private By colvisInstallationIDVisibility = By.xpath("//li[(@data-cv-idx='8') and contains(@class, 'active')]");
    private By colvisPhone = By.xpath("//li[@data-cv-idx='9']");
    private By colvisPhoneVisibility = By.xpath("//li[(@data-cv-idx='9') and contains(@class, 'active')]");
    private By colvisBillingCustomerNr = By.xpath("//li[@data-cv-idx='10']");
    private By colvisBillingCustomerNrVisibility = By.xpath("//li[(@data-cv-idx='10') and contains(@class, 'active')]");
    private By colvisBillingCompanyName = By.xpath("//li[@data-cv-idx='11']");
    private By colvisBillingCompanyNameVisibility = By.xpath("//li[(@data-cv-idx='11') and contains(@class, 'active')]");
    private By colvisBillingAddress = By.xpath("//li[@data-cv-idx='12']");
    private By colvisBillingAddressVisibility = By.xpath("//li[(@data-cv-idx='12') and contains(@class, 'active')]");
    private By colvisDueDate = By.xpath("//li[@data-cv-idx='13']");
    private By colvisDueDateVisibility = By.xpath("//li[(@data-cv-idx='13') and contains(@class, 'active')]");
    private By colvisBillingVATNr = By.xpath("//li[@data-cv-idx='14']");
    private By colvisBillingVATNrVisibility = By.xpath("//li[(@data-cv-idx='14') and contains(@class, 'active')]");
    public By colvisActions = By.xpath("//li[@data-cv-idx='15']");
    public By colvisActionsVisibility = By.xpath("//li[(@data-cv-idx='15') and contains(@class, 'active')]");
    private By colvisRestore = By.xpath("//li[@class='dt-button buttons-columnVisibility reset-columnVisibility']");
    private By onlyDemoToggle = By.xpath("//span[@class='dt-filter']/label[@for='only_demo_customers']");
    private By showBillingToggle = By.xpath("//span[@class='dt-filter']/label[@for='show_billing_info']");
    private By searchInput = By.xpath("//input[@id='dt-search-0']");
    public By clientNameColumnTitle = By.xpath("//th[@data-field='name']");
    public By addressColumnTitle = By.xpath("//th[@data-field='client_address']");
    private By pmsColumnTitle = By.xpath("//th[@data-field='pms']");
    private By categoryColumnTitle = By.xpath("//th[@data-field='category_name']");
    public By demoColumnTitle = By.xpath("//th[@data-field='is_demo']");
    private By refColumnTitle = By.xpath("//th[@data-field='ref']");
    public By actionsColumnTitle = By.xpath("//th[@data-field='action']");
    private By idColumnTitle = By.xpath("//th[@data-field='id']");
    private By countryColumnTitle = By.xpath("//th[@data-field='country_name']");
    private By installationIDColumnTitle = By.xpath("//th[@data-field='installation_id']");
    private By phoneColumnTitle = By.xpath("//th[@data-field='phone']");
    private By billingCustomerNrColumnTitle = By.xpath("//th[@data-field='billing_customer_number']");
    private By billingCompanyNameColumnTitle = By.xpath("//th[@data-field='billing_company_name']");
    private By billingAddressColumnTitle = By.xpath("//th[@data-field='billing_address']");
    private By dueDateColumnTitle = By.xpath("//th[@data-field='due_date']");
    private By billingVATNrColumnTitle = By.xpath("//th[@data-field='billing_vat_number']");
    private By editButton = By.xpath("(//i[@class='fa fa-pencil'])[1]");
    private By copyDemoClientButton = By.xpath("(//i[@class='fa fa-copy'])[1]");
    private By deleteButton = By.xpath("(//i[@class='fa fa-trash'])[1]");
    private By rowsPerPagedropdown = By.xpath("//select[@id='dt-length-0']");
    public By option5rowsPerPage = By.xpath("//select[@id='dt-length-0']/option[@value='5']");
    public By option10rowsPerPage = By.xpath("//select[@id='dt-length-0']/option[@value='10']");
    public By option25rowsPerPage = By.xpath("//select[@id='dt-length-0']/option[@value='25']");
    public By option50rowsPerPage = By.xpath("//select[@id='dt-length-0']/option[@value='50']");
    public By option100rowsPerPage = By.xpath("//select[@id='dt-length-0']/option[@value='100']");
    public By optionAllrowsPerPage = By.xpath("//select[@id='dt-length-0']/option[@value='-1']");
    private By firstPageArrow = By.xpath("//button[@data-dt-idx='first']");
    private By previousPageButton = By.xpath("//button[@data-dt-idx='previous']");
    private By nextPageButton = By.xpath("//button[@data-dt-idx='next']");
    private By lastPageArrow = By.xpath("//button[@data-dt-idx='last']");
    private By page1Button = By.xpath("//button[@data-dt-idx='0']");
    private By page2Button = By.xpath("//button[@data-dt-idx='1']");
    private By lastPageButton = By.xpath("//button[@data-dt-idx='next']/preceding-sibling::button[1]");

    //Actions
    public void clickCustomerMenu() {
        Actions.clickElement(customersMenu);
    }

    public void clickAddNewCustomerButton() {
        Actions.waitForOverlaysToDisappear(By.xpath("//div[@class='spinner']"));
        Actions.clickElement(newCustomer);
    }

    public void clickColvisDropdown() {
        Actions.clickElement(colvisDropdown);
    }

    public void clickIDColvis() {
        Actions.clickElement(colvisID);
    }

    public void clickActionsColvis() {
        Actions.clickElement(colvisActions);
    }

    public void clickToShowHideColumns(By xpath) {
        Actions.waitForOverlaysToDisappear(By.xpath("//div[@class='spinner']"));
        Actions.clickElement(xpath);
    }

    //Assertions
    public void verifyAtCustomersPage() {
        Assert.assertTrue(BrowserManager.getDriver().getCurrentUrl().equals("http://dev3.vitex.asia:8009/en/admin/clients"), "FAIL. Not at EventsOverview Page");
    }

    public void verifyAtAddNewCustomerPage() {
        Assert.assertTrue(BrowserManager.getDriver().getCurrentUrl().contains("clients/create"), "FAIL. Not at Add New Customer Page");
    }

    public boolean verifyColumnVisibility(By xpath) {
        try {
            boolean isColumnVisible = Actions.verifyElementVisibility(xpath);
            if (isColumnVisible) {
                return true; // Column is visible
            } else {
                return false; // Column is not displayed
            }
        } catch (Exception e) {
            WriteLogs.error("Error while verifying Column visibility: " + xpath);
            e.printStackTrace();
            return false; //If there is an error, assume that the Column is not displayed

        }

    }

    public boolean verifyColumnInvisibility(By xpath) {
        try {
            boolean isColumnInvisible = !Actions.checkElementExist(xpath);
            if (isColumnInvisible) {
                return true; // Column is invisible
            } else {
                return false; // Column is still displayed
            }
        } catch (Exception e) {
            WriteLogs.error("Error while verifying column invisibility: " + xpath);
            e.printStackTrace();
            return false; //If there is an error, assume that the column is still displayed
        }
    }

    public boolean verifyElementVisibility(By xpath) {
        try {
            boolean isElementVisible = Actions.isElementDisplayed(xpath);
            if (isElementVisible) {
                return true; // Element is visible
            } else {
                return false; // Element is not displayed
            }
        } catch (Exception e) {
            WriteLogs.error("Error while verifying Element visibility: " + xpath);
            e.printStackTrace();
            return false; //If there is an error, assume that the Element is not displayed

        }
    }

    public boolean verifyElementInvisibility(By xpath) {
        try {
            boolean isElementInvisible = !Actions.isElementDisplayed(xpath);
            if (isElementInvisible) {
                return true; // Element is invisible
            } else {
                return false; // Element is still displayed
            }
        } catch (Exception e) {
            WriteLogs.error("Error while verifying column invisibility: " + xpath);
            e.printStackTrace();
            return false; //If there is an error, assume that the Element is still displayed
        }
    }

    public void clickRestoreColvis() {
        Actions.clickElement(colvisRestore);
    }

    // Check if the element is 'active' in COLVIS DROPDOWN
    public boolean isActiveInColvisDropdown(By xpath) {

        try {
            Actions.scrollToElement(xpath);
            // Check if the element is found and visible
            boolean isActive = BrowserManager.getDriver().findElements(xpath).size() > 0;
            if (isActive) {
                WriteLogs.info("Element is active in COLVIS dropdown: " + xpath);
            } else {
                WriteLogs.info("Element is not active in COLVIS dropdown: " + xpath);
            }
            return isActive;
        } catch (Exception e) {
            // Log the exception and continue execution
            WriteLogs.error("Error while checking if element is active in COLVIS dropdown: " + xpath + ". " + e.getMessage());
            return false;
        }
    }

    // Check if the element is not 'active' in COLVIS DROPDOWN
    public boolean isNotActiveInColvisDropdown(By xpath) {
        try {
            // Try to find the elements matching the XPath
            List<WebElement> elements = BrowserManager.getDriver().findElements(xpath);

            // If no elements are found, assume the element is not active
            if (elements.isEmpty()) {
                WriteLogs.info("Element is not active in COLVIS dropdown (element not found): " + xpath);
                return true; // Return true because it's not active
            }

            // If elements are found, check if they are visible/active
            boolean isNotActive = elements.stream().noneMatch(WebElement::isDisplayed);
            if (isNotActive) {
                WriteLogs.info("Element is not active in COLVIS dropdown: " + xpath);
            } else {
                WriteLogs.info("Element is active in COLVIS dropdown: " + xpath);
            }
            return isNotActive;
        } catch (Exception e) {
            // Log unexpected exceptions but assume the element is not active
            WriteLogs.error("Unexpected error while checking if element is not active in COLVIS dropdown: " + xpath + ". " + e.getMessage());
            return true; // Assume not active in case of an error
        }
    }

    public void clickOnlyDemo() {

        try {
            Actions.clickElement(onlyDemoToggle);
            WriteLogs.info("Clicked the Only Demo toggle.");
        } catch (Exception e) {
            WriteLogs.error("Failed to click the Only Demo toggle: " + e.getMessage());
        }
    }

    public boolean verifyOnlyDemoToggleOn() {
        // Locate the <i> element
        By toggleIconXpath = By.xpath("//span[@class='dt-filter']/label[@for='only_demo_customers']//i");

        try {
            // Find the WebElement
            WebElement toggleIcon = BrowserManager.getDriver().findElement(toggleIconXpath);

            // Check if the class contains "fa-toggle-on"
            boolean isToggleOn = toggleIcon.getAttribute("class").contains("fa-toggle-on");

            // Use the boolean value as needed
            if (isToggleOn) {
                WriteLogs.info("Demo toggle is ON.");
                return true;
            } else {
                WriteLogs.info("Demo toggle is OFF.");
                return false;
            }
        } catch (NoSuchElementException e) {
            WriteLogs.error("Toggle element not found: " + e.getMessage());
        }
        return false;
    }


    public void VerifyClientsAreDemo() {
        Actions.waitForPageLoaded();
        // Get a list of all the rows from the customer table
        List<WebElement> clientRows = BrowserManager.getDriver().findElements(By.cssSelector(".mdc-data-table__row"));
//// Filter the rows to only include those that are displayed and not hidden by CSS
//        clientRows = clientRows.stream()
//                .filter(row -> isRowVisible(row))  // Only include rows that are truly visible
//                .collect(Collectors.toList());

        // Browse through each visible row and check for the presence of the 'demo' icon
        for (WebElement row : clientRows) {
            try {
                WebElement demoCheckIcon = row.findElement(By.cssSelector(".fa-check.fa-stack-1x.text-success"));
                // Check if the demo icon is displayed
                Assert.assertTrue(demoCheckIcon.isDisplayed(), "Client is not a demo client: " + row.getText());
            } catch (NoSuchElementException e) {
                // Handle the case when the demo check icon is not found for a client
                WriteLogs.error("Demo icon not found for client: " + row.getText());
                Assert.fail("Demo icon not found for client: " + row.getText());
            } catch (Exception e) {
                // Catch any other unexpected errors
                WriteLogs.error("An unexpected error occurred while verifying demo client: " + row.getText());
                e.printStackTrace();
                Assert.fail("An unexpected error occurred while verifying demo client: " + row.getText());
            }
        }
    }

    private boolean isRowVisible(WebElement row) {
        // Check if the row is displayed using isDisplayed()
        if (!row.isDisplayed()) {
            return false;
        }

        // Check if the row is visible in the layout (not hidden by CSS styles)
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        String visibility = js.executeScript("return window.getComputedStyle(arguments[0]).visibility;", row).toString();
        String display = js.executeScript("return window.getComputedStyle(arguments[0]).display;", row).toString();

        // Return false if the element is hidden or not displayed properly
        return !"hidden".equals(visibility) && !"none".equals(display);
    }

    public boolean verifyShowBillingInfoToggleOn() {
        try {
            Actions.waitForElementVisible(showBillingToggle);
            Actions.waitForOverlaysToDisappear(By.xpath("//div[@class='spinner']"));
            boolean isToggleOn = Actions.isElementSelected(showBillingToggle);
            if (isToggleOn) {
                return true; // Toggle is on
            } else {
                return false; // Toggle is off
            }
        } catch (Exception e) {
            WriteLogs.error("Show billing info toggle is off or cannot be enabled");
            e.printStackTrace();
            return false;

        }
    }

    public void clickShowBillingInfoToggle() {
        Actions.clickElement(showBillingToggle);
    }

    public void makeColumnVisible(By targetColumnXpath, List<By> columnsToHideXpaths) {

        try {
            // Step 1: Check if the target column is already visible
            boolean isTargetColumnVisible = verifyColumnVisibility(targetColumnXpath);

            // Step 2: If not visible, start hiding active columns iteratively
            while (!isTargetColumnVisible && !columnsToHideXpaths.isEmpty()) {
                if (verifyElementInvisibility(By.xpath("//li[@class='dt-button buttons-columnVisibility reset-columnVisibility']"))) {
                    WriteLogs.info("ColVis dropdown is not open. Opening it now.");
                    clickColvisDropdown();
                }
                Iterator<By> iterator = columnsToHideXpaths.iterator();

                while (iterator.hasNext()) {
                    By columnToHideXpath = iterator.next();

                    // Check if the column to hide is active
                    if (isActiveInColvisDropdown(columnToHideXpath)) {
                        // Hide the column by clicking or toggling its visibility
                        Actions.clickElement(columnToHideXpath);
                        WriteLogs.info("Clicked to hide column: " + columnToHideXpath);

                        // Remove the column from the list after hiding
                        iterator.remove();

                        // Break the loop to recheck the target column visibility
                        break;
                    }
                }

                // Recheck if the target column is now visible
                isTargetColumnVisible = verifyColumnVisibility(targetColumnXpath);
            }
            Actions.waitForPageLoaded();
            // Log the final result
            if (isTargetColumnVisible) {
                WriteLogs.info("Target column is now visible: " + targetColumnXpath);
            } else {
                WriteLogs.error("Unable to make the target column visible after hiding other columns.");
            }
        } catch (Exception e) {
            WriteLogs.error("Error while ensuring column visibility: " + targetColumnXpath + ". " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickEditCustomer() {
        //Show ID column if hidden
        clickColvisDropdown();
        if (isNotActiveInColvisDropdown(colvisIDVisibility)) {
            clickIDColvis();
        }

        //Refresh to close colvis dropdown
        Actions.pressF5();

        // Get the ID of the first record
        String firstRecordID = Actions.getElementText(By.xpath("//table[@id='DataTables_Table_0']//tbody/tr[1]/td[1]"));

        // Click the "Edit" button of the first record
        Actions.clickElement(editButton);

        // Wait for the page to navigate and get the current URL
        String actualURL = BrowserManager.getDriver().getCurrentUrl();

        // Construct the expected URL using the base URL and the retrieved ID
        String expectedURL = PropertiesHelper.getValue("ADMIN_BASE_URL") + "clients/" + firstRecordID + "/edit";

        // Assert that the actual URL matches the expected URL
        Assert.assertEquals(actualURL,expectedURL,
                "❌ URL mismatch! Expected URL: " + expectedURL + ", but got: " + actualURL
        );

        // Log the result
        WriteLogs.info("✅ URL validation successful. Expected URL: " + expectedURL + " matches the Actual URL: " + actualURL);
    }
}