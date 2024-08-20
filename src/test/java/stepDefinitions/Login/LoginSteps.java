package stepDefinitions.Login;

import ReusableMethods.ExcelDataReader;
import base.PageObjectManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.LoginObjectPage;
import org.openqa.selenium.WebDriver;
import utils.DriverSetup;

import java.io.IOException;
import java.util.List;

public class LoginSteps {
    private WebDriver driver;
    private PageObjectManager reusableMethod;
    private LoginObjectPage financePage = new LoginObjectPage();

    public LoginSteps(DriverSetup driverSetup) {
        this.driver = driverSetup.driver;
        this.reusableMethod = new PageObjectManager(driver);
    }

    @When("User enter valid username and password in login form")
    public void userEnterValidUsernameAndPasswordInLoginForm() throws IOException, InterruptedException {
        String filePath = "TestData/Credentials.xlsx";
        String sheetName = "Sheet1";
        List<String[]> excelData = ExcelDataReader.readDataFromExcel(filePath, sheetName);

        for (String[] row : excelData) {
            if (row.length >= 2){
                String username1 = row[0];
                String password = row[1];
                reusableMethod.sendKeysToElement(financePage.emailTextField, username1);
                Thread.sleep(2000);
                reusableMethod.sendKeysToElement(financePage.passwordTextField, String.valueOf(password));
                Thread.sleep(2000);
            }else {
                System.out.println("Invalid data format in Excel row.");

            }

        }
    }
    @And("Clicks on Log in button")
    public void clicksOnLogInButton() throws InterruptedException {
        reusableMethod.clickElement(financePage.loginButton);
        Thread.sleep(8000);

    }

    @Then("Verify that the home page is displayed")
    public void verifyThatTheHomePageIsDisplayed() throws InterruptedException {
        reusableMethod.isElementDisplayed(financePage.homePageImg);
    }
}
