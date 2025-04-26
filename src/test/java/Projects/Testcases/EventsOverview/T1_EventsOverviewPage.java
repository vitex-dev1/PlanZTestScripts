package Projects.Testcases.EventsOverview;

import Projects.Pages.EventsOverview;
import Login.Testcases.T1_NormalLogin;
import ai.planz.dev.Browsers.TestSetup;
import Common.Actions;
import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.EvidenceHelper;
import ai.planz.dev.helpers.ExcelHelper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T1_EventsOverviewPage extends TestSetup {
    EventsOverview EventsOverview = new EventsOverview();
    T1_NormalLogin T1_NormalLogin = new T1_NormalLogin();
    private ExcelHelper excelHelper;
    DataTables DataTables = new DataTables();

    @BeforeClass
    public void setup() {
        // Initialize the Excel helper once before running tests
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/TestData/DataTables.xlsx", "Search");
    }

    @Test(priority = 1)
    public void loginSuccess() {
        EvidenceHelper.startRecord("loginSuccess");

        T1_NormalLogin.loginSuccess();
    }

    @Test(priority = 2)
    public void accessCustomersPage() {
        Actions.closeDebugBar();
        EventsOverview.clickCustomerMenu();
        EventsOverview.verifyAtCustomersPage();
    }

    @Test(priority = 3)
    public void accessAddNewCustomerPage() {
        EventsOverview.clickAddNewCustomerButton();
        EventsOverview.verifyAtAddNewCustomerPage();
    }

    //Show/Hide Columns. For now write for ID column only
    @Test(priority = 4)
    public void clickToShowHideIDColumns() {

        EventsOverview.clickCustomerMenu();
        Actions.sleep(3);
        EventsOverview.clickColvisDropdown();

        //Check if the element is 'active' or not in COLVIS DROPDOWN

        if (EventsOverview.isActiveInColvisDropdown(By.xpath("//li[@data-cv-idx='0']"))) {

            //Check if the element is 'active' or not in GRID VIEW after clicking in colvis dropdown
            EventsOverview.clickToShowHideColumns(By.xpath("//li[@data-cv-idx='0']"));
            EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='0']"));
        } else {
            EventsOverview.clickToShowHideColumns(By.xpath("//li[@data-cv-idx='0']"));
            EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='0']"));
        }
    }

    //Restore colvis
    @Test(priority = 5)
    public void restoreColvis() {
        //Check if the ColVis dropdown is open. If not, open it
        if (EventsOverview.verifyElementInvisibility(By.xpath("//li[@class='dt-button buttons-columnVisibility reset-columnVisibility']"))) {
            WriteLogs.info("ColVis dropdown is not open. Opening it now.");
            EventsOverview.clickColvisDropdown();
        }

        EventsOverview.clickRestoreColvis();

        //Check to display only the default columns in GRID VIEW
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='1']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='2']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='4']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='5']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='6']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='7']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='15']"));

        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='0']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='3']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='8']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='9']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='10']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='11']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='12']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='13']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='14']"));

        //Check to Tick only the default columns in the ColVis dropdown
        EventsOverview.clickColvisDropdown();
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='1') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='2') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='4') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='5') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='6') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='7') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='15') and contains(@class, 'active')]"));

        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='0') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='3') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='8') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='9') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='10') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='11') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='12') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='13') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='14') and contains(@class, 'active')]"));

        //Close Colvis dropdown
        Actions.scrollAndClickAtCoordinates(250, 498);
    }

    //Only Demo Toggle
    @Test(priority = 6)
    public void onlyDemoToggle() {
        //Check if the only Demo Toggle is on. If not, enable it

        if (!EventsOverview.verifyOnlyDemoToggleOn()) {
            WriteLogs.info("Only Demo toggle is off. Enabling it now.");
            EventsOverview.clickOnlyDemo();

        }
        //Double check if the only Demo Toggle is enabled
        EventsOverview.verifyOnlyDemoToggleOn();

        //Check to display only the demo clients in GRID VIEW
        // Columns to hide (selectors for column headers)
        List<By> columnsToHideXpaths = new ArrayList<>(Arrays.asList(
                EventsOverview.colvisClientName, EventsOverview.colvisActions, EventsOverview.colvisAddress
        ));

        EventsOverview.makeColumnVisible(EventsOverview.demoColumnTitle, columnsToHideXpaths);

        EventsOverview.VerifyClientsAreDemo();
    }

    //Show billing info toggle
    @Test(priority = 7)
    public void showBillingInfoToggle() {
        //Reload page
        Actions.pressF5();
        //Check if the Show billing info Toggle is on. If not, enable it

        if (!EventsOverview.verifyShowBillingInfoToggleOn()) {
            WriteLogs.info("Show billing info toggle is off. Enabling it now.");
            EventsOverview.clickShowBillingInfoToggle();

        }
        //Double check if the Show billing info Toggle is enabled
        EventsOverview.verifyShowBillingInfoToggleOn();

        //Check to display only the billing info in GRID VIEW
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='1']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='10']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='11']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='12']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='13']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='14']"));
        EventsOverview.verifyColumnVisibility(By.xpath("//li[@data-cv-idx='15']"));

        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='0']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='3']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='8']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='9']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='2']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='4']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='5']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='6']"));
        EventsOverview.verifyColumnInvisibility(By.xpath("//li[@data-cv-idx='7']"));

        //Check to Tick only the billing info related rows in the ColVis dropdown
        EventsOverview.clickColvisDropdown();
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='1') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='10') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='11') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='12') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='13') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='14') and contains(@class, 'active')]"));
        EventsOverview.isActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='15') and contains(@class, 'active')]"));

        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='0') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='3') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='8') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='9') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='2') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='4') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='5') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='6') and contains(@class, 'active')]"));
        EventsOverview.isNotActiveInColvisDropdown(By.xpath("//li[(@data-cv-idx='7') and contains(@class, 'active')]"));

        //Close Colvis dropdown
        Actions.scrollAndClickAtCoordinates(270, 17);
    }

    //Pagination
    @Test(priority = 8)
    public void pagination() {
        Actions.pressF5();
        //Turn off the demo toggle to display all records. If it is already off, do nothing
        if (EventsOverview.verifyOnlyDemoToggleOn()) {
            WriteLogs.info("Only Demo toggle is on. Turning off the demo toggle to display all records.");
            EventsOverview.clickOnlyDemo();
        } else {
            WriteLogs.info("Only Demo toggle is already off. Proceeding to the next step.");
        }
        // Fetch total record count
        Actions.waitForPageLoaded();
        DataTables.extractTotalRecords();

        //Verify that the dropdown has exactly six options (5, 10, 25, 50, 100, and All) when opened
        DataTables.clickRowsPerPageDropdown();
        DataTables.verifyRowsPerPageOptionsVisibility();

        //Test each option in the dropdown

        // Set the dropdown to display All rows
        Actions.clickElement(EventsOverview.optionAllrowsPerPage);
        Assert.assertEquals(DataTables.getLastPageNumber(), 1, "Number of pages mismatch!");
        //Verify If there is 1 page, the records on page 1 must be less than or equal to 5,
        // and if there are more than 1 page, the records on page 1 must be exactly 5
        DataTables.checkNumberOfRowsPerPage(Integer.MAX_VALUE);

        // Set the dropdown to display 10 rows per page
        Actions.clickElement(DataTables.option10rowsPerPage);
        // Verify the number of pages
        Assert.assertEquals(DataTables.getLastPageNumber(), DataTables.expectedPages(DataTables.totalRecords, 10), "Number of pages mismatch!");
        DataTables.checkNumberOfRowsPerPage(10);

        // Set the dropdown to display 25 rows per page
        Actions.clickElement(DataTables.option25rowsPerPage);
        // Verify the number of pages
        Assert.assertEquals(DataTables.getLastPageNumber(), DataTables.expectedPages(DataTables.totalRecords, 25), "Number of pages mismatch!");
        DataTables.checkNumberOfRowsPerPage(25);

        // Set the dropdown to display 50 rows per page
        Actions.clickElement(DataTables.option50rowsPerPage);
        // Verify the number of pages
        Assert.assertEquals(DataTables.getLastPageNumber(), DataTables.expectedPages(DataTables.totalRecords, 50), "Number of pages mismatch!");
        DataTables.checkNumberOfRowsPerPage(50);

        // Set the dropdown to display 5 rows per page
        Actions.clickElement(DataTables.option5rowsPerPage);
        Actions.waitForPageLoaded();
        // Verify the number of pages
        Assert.assertEquals(DataTables.getLastPageNumber(), DataTables.expectedPages(DataTables.totalRecords, 5), "Number of pages mismatch!");
        //Verify If there is 1 page, the records on page 1 must be less than or equal to 5,
        // and if there are more than 1 page, the records on page 1 must be exactly 5
        DataTables.checkNumberOfRowsPerPage(5);

        //Ensure that pagination works as expected when clicking on the page numbers and navigation arrows
        DataTables.validatePaginationButtons();
        EvidenceHelper.stopRecord();
    }

    @Test(priority = 9)
    public void searchCustomers() {
        //Reset table to a state without filters.
        if (EventsOverview.verifyOnlyDemoToggleOn()) {
            WriteLogs.info("Only Demo toggle is on. Turning off the demo toggle to display all records.");
            EventsOverview.clickOnlyDemo();
        } else {
            WriteLogs.info("Only Demo toggle is already off. Proceeding to the next step.");
        }

        DataTables.showAllHiddenColumns();
        WriteLogs.info("Zooming out to display all columns...");
        Actions.zoomOutZoomIn(.7);
        Actions.sleep(2);
        Actions.pressF5();

        WriteLogs.info("Searching with a common word...");
        DataTables.searchDataTables(excelHelper.getCellData(1, 1));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 1));
        DataTables.searchDataTables(excelHelper.getCellData(1, 2));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 2));
        DataTables.searchDataTables(excelHelper.getCellData(1, 3));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 3));


        WriteLogs.info("Searching with special characters...");
        DataTables.searchDataTables(excelHelper.getCellData(1, 4));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 4));

        WriteLogs.info("Searching with a sentence...");
        DataTables.searchDataTables(excelHelper.getCellData(1, 5));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 5));
        DataTables.searchDataTables(excelHelper.getCellData(1, 6));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 6));

        //Clear search and compare the number of rows in the table to the number of rows before searching
        WriteLogs.info("Clearing search...");
        DataTables.verifyClearSearch();

        WriteLogs.info("Searching for a word not in the table...");
        DataTables.searchDataTables(excelHelper.getCellData(1, 8));
        DataTables.verifyNoSearchResults("No data available in table");

        WriteLogs.info("Searching for a word not in the table - combined with filter (Only demo)...");
        //Check if the only Demo Toggle is on. If not, enable it
        if (!EventsOverview.verifyOnlyDemoToggleOn()) {
            WriteLogs.info("Only Demo toggle is off. Enabling it now.");
            EventsOverview.clickOnlyDemo();

        }
        //Double check if the only Demo Toggle is enabled
        EventsOverview.verifyOnlyDemoToggleOn();
        DataTables.searchDataTables(excelHelper.getCellData(1, 9));
        DataTables.verifyNoSearchResults("No data available in table");

        WriteLogs.info("Search combined with filter (Only demo)");
        DataTables.searchDataTables(excelHelper.getCellData(1, 7));
        DataTables.verifySearchResults(excelHelper.getCellData(1, 7));

    }

    //Reorder columns
    @Test(priority = 10)
    public void reorderColumns() {
        //Show ID and Actions columns if they are hidden to verify their order
        EventsOverview.clickColvisDropdown();
        if (EventsOverview.isNotActiveInColvisDropdown(EventsOverview.colvisIDVisibility)) {
            EventsOverview.clickIDColvis();
        }
        if (EventsOverview.isNotActiveInColvisDropdown(EventsOverview.colvisActionsVisibility)) {
            EventsOverview.clickActionsColvis();
        }

        //Reload page to close the ColVis dropdown
        Actions.pressF5();

        DataTables.validateFirstAndLastColumns("ID", "Actions");

    }

    @Test(priority = 11)
    public void clickEditCustomer() {
        EventsOverview.clickEditCustomer();
    }
}