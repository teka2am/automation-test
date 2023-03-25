package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import configuration.TestConfigs;
import logging.Log;
import utilities.DateTimeUtilities;

import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private static ThreadLocal<ExtentReports> tl_extent = new ThreadLocal<>();
    private static ThreadLocal<List<ExtentTest>> tl_testChain = new ThreadLocal<>();

    public static synchronized void startReport() {
        String reportPath = TestConfigs.getReportFolder() + "TestReport_" + DateTimeUtilities.getCurrentDateAsFormatString("yy_MM_dd_HHmmss") + ".html";

        tl_extent.set(new ExtentReports());
        tl_testChain.set(new ArrayList<>());
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        tl_extent.get().attachReporter(spark);
    }

    public static synchronized void finishing() {
        tl_extent.get().flush();
        tl_testChain.get().clear();
        tl_extent.set(null);
    }

    public static synchronized ExtentReports getReport() {
        if (tl_extent.get() == null) {
            startReport();
        }
        return tl_extent.get();
    }

    public static synchronized void createNewTestNode(String testNodeName) {
        if (tl_testChain.get().size() > 0) {
            tl_testChain.get().add(getCurrentTest().createNode(testNodeName));
        } else {
            tl_testChain.get().add(tl_extent.get().createTest(testNodeName));
        }
    }

    public static synchronized ExtentTest getCurrentTest() {
        if (tl_testChain.get().isEmpty()) {
            createNewTestNode("Default Test -- created automatically");
            Log.warning("Report currently does not have any test/step. Started a new one.");
        }
        return tl_testChain.get().get(tl_testChain.get().size() - 1);
    }

    public static synchronized void endCurrentTestNode() {
        tl_testChain.get().remove(tl_testChain.get().size() - 1);
    }


}
