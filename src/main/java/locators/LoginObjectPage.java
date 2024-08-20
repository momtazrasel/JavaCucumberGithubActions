package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginObjectPage {
    public static WebDriver driver;

    // Login Page
    public By loginButton = By.xpath("(//button[@type='submit'])[1]");
    public By emailTextField = By.xpath("(//input[@id='email'])[1]");
    public By passwordTextField = By.xpath("(//input[@id='password'])[1]");
    public By homePageImg = By.xpath("(//img[@alt='logo'])[1]");
//    public By invalidTest = By.xpath("(//span[normalize-space()='Invalid credentials'])[1]");
    public By invalidTest = By.xpath("//div[@class = 'ant-message-custom-content ant-message-er+ror']");




}
