package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    static ExtentReports extent;
    static ExtentTest test;

    public static ExtentReports getExtent() {
        if (extent == null) {
            ExtentSparkReporter r = new ExtentSparkReporter("reports/TestReport.html");
            extent = new ExtentReports();
            extent.attachReporter(r);
        }
        return extent;
    }

    public static ExtentTest createTest(String name) {
        test = getExtent().createTest(name);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

}