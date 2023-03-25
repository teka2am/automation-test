package configuration;

import logging.Log;
import reporting.Reporter;
import utilities.JavaUtilities;
import utilities.PropertiesReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TestConfigs {
    private static final String TEST_CONFIGS_FOLDER_NAME = "test_config";
    private static final String TEST_CONFIGS_FILE_NAME = "test.properties";
    public static final String TEST_PROPERTIES_BROWSER_NAME = "browser.name";
    public static final String TEST_PROPERTIES_REPORT_FOLDER = "report.folder";
    public static final String TEST_PROPERTIES_TEST_SITE_URL = "test.site.URL";
    public static final String DEFAULT_BROWSER_NAME = "chrome";
    public static final String DEFAULT_REPORT_FOLDER = "target/ExtentReports/Spark/";

    private static String getTestConfigFolder() {
        Path configPath = Paths.get(System.getProperty("user.dir"), TEST_CONFIGS_FOLDER_NAME);
        if (!Files.exists(configPath)) {
            JavaUtilities.createDirectoryPath(configPath.toString());
        }
        return configPath.toString();
    }

    private static String getTestConfigFile() {
        return getTestConfigFolder() + System.getProperty("file.separator") + TEST_CONFIGS_FILE_NAME;
    }

    private static Properties getTestConfigProperties() {
        Properties testConfig = PropertiesReader.loadPropertiesFile(getTestConfigFile());
        if (testConfig == null) {
            Log.info("[" + TEST_CONFIGS_FILE_NAME + "] does not exist!!! All configurations for Testing Web App will be default");
        }
        return testConfig;
    }

    public static String getBrowserName() {
        String browserName = getTestConfigProperties().getProperty(TEST_PROPERTIES_BROWSER_NAME).toLowerCase();
        if (browserName == null || browserName.isEmpty()) {
            Reporter.warning("Missing Test Configuration. Property [" + TEST_PROPERTIES_BROWSER_NAME + "] is not defined or empty. Default [" + TEST_PROPERTIES_BROWSER_NAME + "] will be set as [" + DEFAULT_BROWSER_NAME + "]");
            return DEFAULT_BROWSER_NAME;
        } else {
            return browserName;
        }
    }

    public static String getReportFolder() {
        String reportFolder = getTestConfigProperties().getProperty(TEST_PROPERTIES_REPORT_FOLDER);
        if (reportFolder == null || reportFolder.isEmpty()) {
            Reporter.warning("Missing Test Configuration. Property [" + TEST_PROPERTIES_REPORT_FOLDER + "] is not defined or empty. Default [" + TEST_PROPERTIES_REPORT_FOLDER + "] will be set as [" + DEFAULT_REPORT_FOLDER + "]");
            return DEFAULT_REPORT_FOLDER;
        } else {
            return reportFolder;
        }
    }

    public static String getTestSiteURL() {
        String testSiteURL = getTestConfigProperties().getProperty(TEST_PROPERTIES_TEST_SITE_URL);
        if (testSiteURL == null || testSiteURL.isEmpty()) {
            Reporter.fail("Missing Test Configuration. Property [" + TEST_PROPERTIES_TEST_SITE_URL + "] is not defined or empty.");
        }
        return testSiteURL;
    }
}
