package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Actions {
    WebDriver driver;

    Actions actions;
    JavascriptExecutor javascriptExecutor;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        sleepInsecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }
    @Test
    public void TC_02() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInsecond(3);
        actions.moveToElement(driver.findElement(By.xpath("//a//span[text()='Bách Hóa Online - Lưu Niệm']"))).perform();
//        sleepInsecond(3);
        driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());
    }

    @Test
    public void TC_04_ClickAndHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

//        String osName = System.getProperty("os.name");
//        Keys keys;

        Keys keys = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        // Tong so chua chon
        List<WebElement> allnumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allnumbers.size(),20);

        // Chon tu 1 -> 12 theo du hang/cot
        actions.clickAndHold(allnumbers.get(0)).moveToElement(allnumbers.get(11)).release().perform();

        // Chon tu 13 -> 15
        actions.keyDown(keys).perform();

        actions.clickAndHold(allnumbers.get(12))
                .moveToElement(allnumbers.get(14)).release().keyUp(keys).perform();
        List<WebElement> allSelectedNumbers = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allSelectedNumbers.size(),15);

//        actions.click(allnumbers.get(12))
//                .moveToElement(allnumbers.get(15))
//                .keyUp(keys).perform();
        sleepInsecond(3);
    }
    @Test
    public void TC_05_ClickAndSelect() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Keys keys = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));

        actions.keyDown(keys).click(allNumbers.get(0)).release().perform();
        actions.keyDown(keys).click(allNumbers.get(2)).release().perform();
        actions.keyDown(keys).click(allNumbers.get(5)).release().perform();
        actions.keyDown(keys).click(allNumbers.get(10)).release().keyUp(keys).perform();

        List<WebElement> allSelectedNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(allSelectedNumbers.size(),4);
        sleepInsecond(3);
    }

    @Test
    public void TC_06_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        if (driver.toString().contains("firefox")){

            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
            sleepInsecond(3);

            actions.doubleClick(doubleClickButton).perform();
            sleepInsecond(2);

            Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
        }
    }
    @Test
    public void TC_07_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement rightClick =  driver.findElement(By.cssSelector("span.context-menu-one"));

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        actions.contextClick(rightClick).perform();
        sleepInsecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
        sleepInsecond(2);

        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        sleepInsecond(2);
        driver.switchTo().alert().accept();
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }
    @Test
    public void TC_08_Fixed_Popup_Not_In_DOM_01() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//div[@id='VIP_BUNDLE']//picture//img")).click();
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInsecond(3);
        //kiem tra pop up hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInsecond(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInsecond(2);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(),"Mật khẩu không được để trống");

        // dong pop up

        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepInsecond(2);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);
    }
    @Test
    public void TC_08_Fix_Popup_Not_In_DOM_02() {
        driver.get("https://facebook.com/");

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInsecond(2);

        //kiem tra pop up hien thi
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInsecond(2);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(),0);
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
