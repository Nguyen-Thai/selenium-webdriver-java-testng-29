package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Radio_Checkbox {
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
    public void TC_01_Telerik_Checkbox() {
        By HeatedFrontCheckBox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::span//input");
        By RearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span//input");
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        // Chon 2 checkbox
        // Case 1 Neu nhu mo app ra ma checkbox da duoc chon roi thi sao
//        if (!driver.findElement(RearSideCheckbox).isSelected()){
//            driver.findElement(RearSideCheckbox).click();
//            sleepInsecond(3);
//        }
        checkToElement(HeatedFrontCheckBox);

        // Case 2 Neu nhu mo app ra ma checkbox chua duoc chon thi sao
//        if (!driver.findElement(HeatedFrontCheckBox).isSelected()){ // neu ket qua true moi chay vao than ham if
//            driver.findElement(HeatedFrontCheckBox).click();
//            sleepInsecond(3);
//        }
        checkToElement(RearSideCheckbox);
        Assert.assertTrue(driver.findElement(RearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(HeatedFrontCheckBox).isSelected());

        // Bo chon 2 checkbox bang cach click vao 1 lan nua

        uncheckToElement(HeatedFrontCheckBox);
        uncheckToElement(RearSideCheckbox);
        Assert.assertFalse(driver.findElement(RearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(HeatedFrontCheckBox).isSelected());

    }
    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::span//input");
        By twoDiesel = By.xpath("//label[text()='2.0 Diesel, 103kW']//preceding-sibling::span//input");

        checkToElement(twoPetrol);
        sleepInsecond(3);
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());
        Assert.assertFalse(driver.findElement(twoDiesel).isSelected());

        checkToElement(twoDiesel);
        sleepInsecond(3);
        Assert.assertFalse(driver.findElement(twoPetrol).isSelected());
        Assert.assertTrue(driver.findElement(twoDiesel).isSelected());

    }
    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Chon all checkbox
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));

        for (WebElement checkbox : allCheckboxes){
        if (!checkbox.isSelected()){
           checkbox.click();
//           sleepInsecond(1);
    }
        }
        for (WebElement checkbox :allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        // Chon  1 checkbox co value "Heart Attack"

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
        for (WebElement checkbox : allCheckboxes){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected())
            {
                checkbox.click();
                sleepInsecond(2);
            }
        }
        for (WebElement checkbox : allCheckboxes)
        {
            if (checkbox.getAttribute("value").equals("Heart Attack"))
            Assert.assertTrue(checkbox.isSelected());
            else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }



    }

    @Test
    public void TC_04() {
        driver.get("https://demos.telerik.com/kendo-ui/radiogroup/index");
//        driver.findElement(By.xpath("//div[@class='k-form-field-wrap']//span//input[@value='E-mail']")).click();
//        sleepInsecond(3);
//        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='k-form-field-wrap']//span//input[@value='E-mail']")).isSelected());


        By registerRadio = By.xpath("//div[@class='k-form-field-wrap']//span//input[@value='E-mail']");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(registerRadio));
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());
//
//        JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
//        jsExecute.executeScript("");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    // Neu nhu Element chua duoc chon thi click de no chon
    public void checkToElement (By byXpath){
        if (!driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInsecond(3);
        }
    }
    // New nhu Element da duoc chon thi click lan nua cho no bo chon
    public void uncheckToElement (By byXpath){
        if (driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInsecond(3);
        }
    }
    public void sleepInsecond (long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
