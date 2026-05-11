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
        try {
            Object obj = result.getInstance();
            java.lang.reflect.Field f = obj.getClass().getDeclaredField("driver");
            f.setAccessible(true);
            WebDriver driver = (WebDriver) f.get(obj);
            if (driver != null) {
                String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String name = result.getName() + "_" + time + ".png";
                new File("screenshots").mkdirs();
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(src.toPath(), new File("screenshots/" + name).toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}