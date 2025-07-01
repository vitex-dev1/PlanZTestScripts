package ai.planz.dev.Logs;
import ai.planz.dev.Browsers.BrowserManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
public class AllureReportManager {
    //Text attachments for Allure
        @Attachment(value = "{0}", type = "text/plain")
        public static String saveTextLog(String message) {
            return message;
        }

        //Screenshot attachments for Allure
        @Attachment(value = "Page screenshot", type = "image/png")
        public static byte[] saveScreenshotPNG() {
            return ((TakesScreenshot) BrowserManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        }
    }

