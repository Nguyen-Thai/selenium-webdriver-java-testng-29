package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    String firstName = "Automation", lastName = "Testing", emailAddress = getEmailAddress(), password = "123456";
    String companyName = "Automation FC", day = "1", month = "May", Year = "1980";

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/register");

        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

//  Verify dropdown day la single
//  Verify dropdown  day 32 items, month 13 items, year 112 items
    Select day = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        Assert.assertFalse(day.isMultiple());
        Assert.assertEquals(day.getOptions().size(),32);
        day.selectByVisibleText(this.day);

    Select month = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        Assert.assertEquals(month.getOptions().size(),13);
        month.selectByVisibleText(this.month);

    Select Year = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
        Assert.assertEquals(Year.getOptions().size(),112);
        Year.selectByVisibleText(this.Year);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInsecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='result']")).getText(),"Your registration completed");
    }
    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInsecond(3);
        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInsecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),emailAddress);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),Year);

    }
    public void sleepInsecond (long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress(){
        Random rand = new Random();
        return "Automation" + rand.nextInt(9999) + "@yopmail.com";
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
