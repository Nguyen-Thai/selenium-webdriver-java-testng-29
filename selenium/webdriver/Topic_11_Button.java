package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Button {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
// Verify button register is disable khi chua click vao
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        Assert.assertTrue(registerButton.isEnabled());

// Lay ra ma mau nen cua button
        String registerBackgroundColorRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB " + registerBackgroundColorRGB);
// Convert tu kieu String (ma RGB) qua kieu Color
        Color registerBackgroundColour = Color.fromString(registerBackgroundColorRGB);
// Convert RGB qua kieu Hexa
        String registerBackgroundColorHexa = registerBackgroundColour.asHex();
        System.out.println("Background color Hexa " + registerBackgroundColorHexa);
//        Assert.assertEquals(registerBackgroundColorHexa,"#ef5a00");
        Assert.assertEquals(Color.fromString(registerButton.getCssValue("background-color")).asHex(),"#ef5a00");
//    Color.fromString(registerButton.getCssValue("background-color")).asHex().toUpperCase();
    }
    @Test
    public void TC_02_fahasha() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

//        Verify login button is disbale and background color
    WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
    Assert.assertFalse(loginButton.isEnabled());
    Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex(),"#000000");
//    Assert.assertEquals(Color.fromString(loginButton).asHex().toUpperCase());
// Nhap email and pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationtesting@yopmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInsecond(3);

        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex(),"#c92127");


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInsecond (long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
