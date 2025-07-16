package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static WebDriver driver;
    public static Properties properties = new Properties();

    public Utils() {
        try {
            FileInputStream inputStream = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources/application.properties"
            );
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Failed to load properties file " + e.getMessage());
        }
    }

    public static void browserIntialization() {
        String browserName = properties.getProperty("browser","edge");
        System.out.println("Initializing " + browserName + " browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Unsupported browser: " + browserName);
        }
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.close();
            System.out.println("Browser closed successfully");
        } else {
            System.out.println("No browser to close");
        }
    }

    public static void quitBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser Quit successfully");
        } else {
            System.out.println("No browser to close");
        }
    }
}
