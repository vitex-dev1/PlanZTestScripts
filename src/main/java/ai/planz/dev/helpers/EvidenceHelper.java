package ai.planz.dev.helpers;

import ai.planz.dev.Logs.WriteLogs;
import org.openqa.selenium.WebDriver;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class EvidenceHelper extends ScreenRecorder { // Create a date-time format to embed into the name of a screenshot or video recording to avoid duplicate names (prevent file overwriting).
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public EvidenceHelper(GraphicsConfiguration cfg) throws IOException, AWTException {
        super(cfg);
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Determine screen size
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();

            // Get the screen with the larger resolution
            Rectangle screenBounds = null;
            for (GraphicsDevice device : gs) {
                DisplayMode dm = device.getDisplayMode();
                if (screenBounds == null || dm.getWidth() * dm.getHeight() > screenBounds.width * screenBounds.height) {
                    screenBounds = device.getDefaultConfiguration().getBounds();
                }
            }

            // Capture screenshot from the larger screen using Robot
            Robot robot = new Robot();
            BufferedImage screenshot = robot.createScreenCapture(screenBounds);

            // Create the folder if it doesn't exist
            File theDir = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            // Save the screenshot with the given name and timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File screenshotFile = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + screenshotName + "_" + timestamp + ".png");

            ImageIO.write(screenshot, "PNG", screenshotFile);

            WriteLogs.info("Screenshot taken: " + screenshotName);
//            WriteLogs.info("Screenshot taken from: " + screenBounds);
        } catch (Exception e) {
            WriteLogs.error("Exception while taking screenshot: " + e.getMessage());
        }
    }

    // Record with Monte Media library
    public static ScreenRecorder screenRecorder;
    public String name;

    //Constructor
    public EvidenceHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    //This function is required to override a custom function in the pre-written library.
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    // To record video
    public static void startRecord(String methodName) {
        //Tạo thư mục để lưu file video vào
        File file = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("RECORDING_PATH"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);
        // Get all screen devices (for multi-monitor support)
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        // Determine the largest available screen
        GraphicsConfiguration gc = null;

        for (GraphicsDevice screen : screens) {
            GraphicsConfiguration config = screen.getDefaultConfiguration();
            Rectangle bounds = config.getBounds();

            if (bounds.width * bounds.height > captureSize.width * captureSize.height) {
                captureSize = bounds;
                gc = config;
            }
        }

        if (gc == null) {
            throw new RuntimeException("No available graphics configuration found.");
        }

        gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        try {
            screenRecorder = new EvidenceHelper(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, methodName);
            screenRecorder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    // Stop recording video
    public static void stopRecord() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


