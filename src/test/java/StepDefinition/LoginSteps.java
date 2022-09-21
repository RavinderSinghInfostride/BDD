package StepDefinition;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;

    By Username = By.xpath("//input[@name='user-name']");
    By Password = By.xpath("//input[@name='password']");
    By Login = By.xpath("//input[@name='login-button']");
    By landingPageVerifyAfterLogin = By.xpath("//span[@class='title']");
    By hamburgerMenuButton = By.xpath("//button[contains(text(),'Open Menu')]");
    By logoutMenuOptionLink = By.xpath("//a[contains(text(),'Logout')]");

    @Given("user is on login page")
    public void user_is_on_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() {
        driver.findElement(Username).sendKeys("standard_user");
        driver.findElement(Password).sendKeys("secret_sauce");
    }

    @And("clicks on login button")
    public void clicks_on_login_button() {
        driver.findElement(Login).click();
    }

    @Then("user is navigated to home page")
    public void user_is_navigated_to_home_page() {
        String isProductPageDisplayed = driver.findElement(landingPageVerifyAfterLogin).getText();
        Assert.assertEquals("PRODUCTS", isProductPageDisplayed);
    }

    @And("user logs out and browser closes")
    public void user_logs_out_and_browser_closes() {
        driver.findElement(hamburgerMenuButton).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutMenuOptionLink));
        driver.findElement(logoutMenuOptionLink).click();
        driver.close();
    }
}