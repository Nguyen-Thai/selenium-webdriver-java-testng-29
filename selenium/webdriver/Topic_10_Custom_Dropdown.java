package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.SizeLimitExceededException;
import java.time.Duration;
import java.util.List;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
//    Tuong minh: trang thai cu the cho element
//    Visible/Invisible/Precense/Number/clickable.....
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Jquery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
//        driver.findElement(By.cssSelector("span#number-button")).click();
//
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
//
//        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
//// allItems dang luu tru 19 item ben trong
////  19 WebElement
//        for (WebElement item : allItems) {
//        String textItem = item.getText();
//        System.out.println("Text item = " + textItem);
////        String
//        if (textItem.equals("8")){
//            item.click();
//            break;
//        }
//        }
        //        Select a speed
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Medium");
        sleepInsecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Medium");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Faster");

        //      Select a file
        selectItemInDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"ui.jQuery.js");
        sleepInsecond(3);
        //      Select a number
        selectItemInDropdown ("span#number-button","ul#number-menu div","8");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"8");
        sleepInsecond(3);
        //     Select a title
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Mrs.");
        sleepInsecond(3);

//     3.1 Neu nhu item can chon no hien thi thi click vao
//     3.2 Neu nhu item can chon nam ben duoi thi 1 so truong hop can scroll xuong hien thi roi moi click
//     4. Truoc khi click can kiem tra neu nhu text cua item bang voi item can chon thi click vao
    }
    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon","div#root span.text","Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
        sleepInsecond(3);
    }
    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        sleepInsecond(3);
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemEditableInDropdown("input.search","div.item>span.text","Andorra");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Andorra");
        sleepInsecond(3);
    }
    public void sleepInsecond (long timeInSecond){
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

    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click(); // "span#number-button"
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));//"ul#number-menu div"
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item : allItems) {
//            String textItem = item.getText();
//            System.out.println("Text item = " + textItem);
            if (item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    };

    public void selectItemEditableInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);// "span#number-button"
        sleepInsecond(3);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));//"ul#number-menu div"
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item : allItems) {
//            String textItem = item.getText();
//            System.out.println("Text item = " + textItem);
            if (item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }

}
