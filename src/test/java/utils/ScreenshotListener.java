package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        Object obj = result.getInstance();
        WebDriver driver = null;
        try {
            driver = (WebDriver) obj.getClass().getDeclaredField("driver").get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driver != null) {
            try {
                String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String name = result.getName() + "_" + time + ".png";
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(src.toPath(), new File("screenshots/" + name).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}