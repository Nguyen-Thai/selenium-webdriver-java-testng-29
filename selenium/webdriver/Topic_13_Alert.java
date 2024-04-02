package webdriver;

import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.codec.binary.Base64;
//import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
//import org.openqa.selenium.devtools.v85.network.Network;
//import org.openqa.selenium.devtools.v85.network.model.Headers;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;

    String projectLocation = System.getProperty("user.dir") ;

    By resultText = By.cssSelector("p#result");
    String promptText = "Automation";

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Cho cho alert precent
        // Neu trong thoi gian cho alert xuat hien thi switch vao
        // Neu trong thoi gian cho alert k xuat hien thi fail

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInsecond(3);
        //Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
       // khi accept or cancel alert se mat luon k con nua
        alert.accept();
        sleepInsecond(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");


    }
    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        sleepInsecond(3);
        alert.dismiss();
        sleepInsecond(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Cancel");


    }
    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInsecond(3);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        sleepInsecond(3);
        alert.sendKeys(promptText);
        sleepInsecond(3);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: " + promptText);

    }
    @Test
    public void TC_04_Authentication_Pass_To_URL() {
        String username = "admin";
        String password = "admin";
        // cach 1 truyen thang usernam and password vao url
        //driver.get("http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // cach 2 tu page A thao tac len 1 element no se qua pagee B (nhung can phai thao tac voi Authen Alert truoc)
        driver.get("http://the-internet.herokuapp.com/");
        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenAlertByUrl(authenLinkUrl, username, password));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        // thu vien alert k support duoc cho Authentication alert
        // Nhung Selenium 4x dung thong qua chrome devtool thay vi thu vien alert
    }

    @Test
    public void TC_05_Authentication_AutoIT() throws IOException {
        // cach 1 truyen thang usernam and password vao url
        //driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        // cach 2 dung autoit (chay duoc tren window, k work tren Linux va MAC)

        Runtime.getRuntime().exec(new String[] { projectLocation + "\\autoIT\\authen_firefox", "admin", "admin"});
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInsecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        // thu vien alert k support duoc cho Authentication alert
        // Nhung Selenium 4x dung thong qua chrome devtool thay vi thu vien alert
    }
    @Test
    public void TC_06_Authentication_Selenium4xx() {

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");



//        // get devtool object
//        DevTools devTools = ((HasDevTools) driver).getDevTools();
//        // start new session
//        devTools.createSession();
//        // Enable the network domain of devtools
//        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//        // Encode username/password
//        Map<String, Object> headers = new HashMap<String, Object>();
//        String basicAuthen = "Basic " + new String(new);
//        headers.put("Authorization", basicAuthen);
//
//        // set to header
//        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
//         driver.get("http://the-internet.herokuapp.com/basic_auth");



        // thu vien alert k support duoc cho Authentication alert
        // Nhung Selenium 4x dung thong qua chrome devtool thay vi thu vien alert
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public String getAuthenAlertByUrl(String url, String username, String password){
    String[] authenArray = url.split("//");
    return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];
    }
    public void sleepInsecond (long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
