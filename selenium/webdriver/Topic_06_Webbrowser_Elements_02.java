package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Webbrowser_Elements_02 {
    //    cac cau lenh thao tac vs browser
//    driver.
    WebDriver driver;

    //    cac cau lenh thao tac vs element
//    element.
    WebElement element;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

//        01
        if (driver.findElement(By.cssSelector("input#email")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#email")).sendKeys("Automation Testing");
            System.out.println("Email is displayed");
        } else {
            System.out.println("Email is not displayed");
        }
        sleepInsecond(3);
//      02
        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 Radio is displayed");
        } else {
            System.out.println("Under 18 Radio is not displayed");
        }
        sleepInsecond(3);
//        03
        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education Textarea is displayed");
        } else {
            System.out.println("Education Textarea is not displayed");
        }
        sleepInsecond(3);
//      04
        if (driver.findElement(By.xpath("//div/h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Name user 5 is displayed");
        } else {
            System.out.println("Name user 5 is not displayed");
        }

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
// 01
        if (driver.findElement(By.cssSelector("input#email")).isEnabled()) {
            System.out.println("Email is Enabled");
        } else {
            System.out.println("Email is Disabled");
        }
// 02
        if (driver.findElement(By.cssSelector("input#under_18")).isEnabled()) {
            System.out.println("Under 18 Radio is Enabled");
        } else {
            System.out.println("Under 18 Radio is Disabled");
        }
//  03
        if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
            System.out.println("Education Textarea is Enabled");
        } else {
            System.out.println("Education Textarea is Disabled");
        }
// 04
        if (driver.findElement(By.cssSelector("select#job1")).isEnabled()) {
            System.out.println("Job Role 01 is Enabled");
        } else {
            System.out.println("Job Role 01 is Disabled");
        }
// 05
        if (driver.findElement(By.cssSelector("select#job2")).isEnabled()) {
            System.out.println("Job Role 02 is Enabled");
        } else {
            System.out.println("Job Role 02 is Disabled");
        }
//  06
        if (driver.findElement(By.cssSelector("input#development")).isEnabled()) {
            System.out.println("Development checkbox is Enabled");
        } else {
            System.out.println("Development checkbox is Disabled");
        }
//  07
        if (driver.findElement(By.cssSelector("input#slider-1")).isEnabled()) {
            System.out.println("slider 1 is Enabled");
        } else {
            System.out.println("slider 1 is Disabled");
        }
// 08
        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password is Enabled");
        } else {
            System.out.println("Password is Disabled");
        }

//  09
        if (driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()) {
            System.out.println("Radio button is Enabled");
        } else {
            System.out.println("Radio button is Disabled");
        }
//  10
        if (driver.findElement(By.cssSelector("textarea#bio")).isEnabled()) {
            System.out.println("Biograpphy Textarea is Enabled");
        } else {
            System.out.println("Biograpphy Textarea is Disabled");
        }

// 11
        if (driver.findElement(By.cssSelector("select#job3")).isEnabled()) {
            System.out.println("Job Role 03 is Enabled");
        } else {
            System.out.println("Job Role 03 is Disabled");
        }
//  12
        if (driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Interest checkbox is Enabled");
        } else {
            System.out.println("Interest checkbox is Disabled");
        }
//  13
        if (driver.findElement(By.cssSelector("input#slider-2")).isEnabled()) {
            System.out.println("slider 2 is Enabled");
        } else {
            System.out.println("slider 2 is Disabled");
        }

    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());
        sleepInsecond(3);

        driver.findElement(By.cssSelector("input#java")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());


    }

    @Test
    public void  TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("testing@yopmail.com");

//        number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("1234");
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
        sleepInsecond(3);
//      lowcase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("nhmfhd");
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
    sleepInsecond(3);
//    uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("GOIRKD");
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
        sleepInsecond(3);

// special character

        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("$%#@%");
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());
        sleepInsecond(3);
//        more than 8 characters - valid

        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Biggggg123#lki67");
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());

    }

    public void sleepInsecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
