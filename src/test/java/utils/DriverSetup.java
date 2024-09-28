package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
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
                    chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                }
<<<<<<< HEAD
                chromeOptions.addArguments("--remote-allow-origins=*");
=======
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/home/runner/work/JavaCucumberGithubActions/JavaCucumberGithubActions/src/test/resources/chromedriver.exe");
                driver = new ChromeDriver(options);
            }
            else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) {
                    options.addArguments("--headless");
                }
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/geckodriver.exe");
                driver = new FirefoxDriver(options);
            }
            else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
>>>>>>> ce45880d873869d089e5de59a9db5cbb1cbe1f1a

                // Automatically manage ChromeDriver version using WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Initialize ChromeDriver with options
                driver = new ChromeDriver(chromeOptions);
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
