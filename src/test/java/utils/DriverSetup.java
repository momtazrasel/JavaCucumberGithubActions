package utils;

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
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties");
        Properties properties = new Properties();
        properties.load(fis);

        String url = properties.getProperty("url");
        String browserProperties = properties.getProperty("browser");
        String browserMaven = System.getProperty("browser");
        String headlessMaven = System.getProperty("headless");

        String browser = browserMaven != null ? browserMaven : browserProperties;
        boolean isHeadless = headlessMaven != null && headlessMaven.equalsIgnoreCase("true");

        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                if (isHeadless) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                }
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
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

            // Initialize the driver and open the URL
            driver.manage().window().maximize();
            driver.get(url);
        }
        return driver;
    }

    public DriverSetup() throws IOException {
        driver = setDriver();
    }
}
