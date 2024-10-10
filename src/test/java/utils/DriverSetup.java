package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverSetup {
    public WebDriver driver;

    public WebDriver setDriver() throws IOException {
        // Load properties from the global properties file
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties");
        Properties properties = new Properties();
        properties.load(fis);

        // Read properties from file and environment
        String url = properties.getProperty("url");
        String browserProperties = properties.getProperty("browser");
        String browserMaven = System.getProperty("browser");
        String headlessMaven = System.getProperty("headless");

        // Determine the browser and headless mode
        String browser = browserMaven != null ? browserMaven : browserProperties;
        boolean isHeadless = headlessMaven != null && headlessMaven.equalsIgnoreCase("true");

        // Initialize WebDriver based on the selected browser
        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                // Setup ChromeOptions
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
//                    chromeOptions.addArguments("--remote-allow-origins=*");
//                    chromeOptions.addArguments("--no-sandbox");  // This disables the sandbox for CI
//                    chromeOptions.addArguments("--disable-dev-shm-usage");  // This resolves potential memory issues in containers
//                    chromeOptions.addArguments("--headless=new");  // This resolves potential memory issues in containers
//                    chromeOptions.addArguments("--disable-gpu");  // Often needed in headless mode
//                    chromeOptions.addArguments("--window-size=1920,1080");  // Optional, depending on test requirements

                }
                chromeOptions.addArguments("--remote-allow-origins=*");

                // Automatically manage ChromeDriver version using WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Initialize ChromeDriver with options
                driver = new ChromeDriver(chromeOptions);
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));  // Page load timeout
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
            // Other browser setups (e.g., Firefox) can go here

            // Maximize window and open the target URL
            driver.manage().window().maximize();
            driver.get(url);
        }
        return driver;
    }

    // Constructor to initialize the WebDriver
    public DriverSetup() throws IOException {
        driver = setDriver();
    }
}
