package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_Actions {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    Actions action;


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }


        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

   // @Test
    public void tc01_hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        //Hover on textbox and verify tooltip is shown

        action.moveToElement(driver.findElement(By.id("age"))).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),"We ask for your age only for statistical purposes.");

    }
    //@Test
    public void tc02_hover(){
        driver.get("https://www.myntra.com/");
        // Hover on Kids option and click on Home & Bath link
        action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"))).perform();
        sleepInSecond(3);
        action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']"))).perform();
        sleepInSecond(2);

        //Verify successful navigation to page
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(),"Kids Home Bath");
    }
   // @Test
    public void tc03_hover(){
        driver.get("https://www.fahasa.com/");
        sleepInSecond(15);


        //close popup promote if it exists
        try {
            driver.findElement(By.id("close-popup")).click();
            sleepInSecond(5);
        } catch (Exception e) {
            // Handle if popup does not exist
        }

        //hover on menu >> hover on FOREIGN BOOKS menu
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        action.moveToElement(driver.findElement(By.cssSelector("a[title='FOREIGN BOOKS']"))).perform();
        sleepInSecond(2);
        By businessManagement = By.xpath("//div[@class='fhs_menu_content fhs_column_left']//span[text()='BUSINESS & MANAGEMENT']");

        //Verify successful BUSINESS & MANAGEMENT submenu is shown
        Assert.assertEquals(driver.findElement(businessManagement).getText(),"BUSINESS & MANAGEMENT");

        //Click on BUSINESS & MANAGEMENT submenu and verify page BUSINESS & MANAGEMENT is shown
        driver.findElement(businessManagement).click();
        Assert.assertEquals(driver.findElement(By.xpath("//strong[string()='Business, Finance & Management']")).getText(), "BUSINESS, FINANCE & MANAGEMENT");

    }
   // @Test
    public void tc04_click_and_hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> listnumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        System.out.println("Tong so luong number= " + listnumber.size());

        //Click and hold from 1 to 4
        action.clickAndHold(listnumber.get(0)).moveToElement(listnumber.get(3)).release().perform();
        sleepInSecond(3);

        List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        System.out.println("Tong so luong number duoc chon= " + listNumberSelected.size());

        //Verify selected number
        Assert.assertEquals(listNumberSelected.size(), 4);

        //Verify selected text

    }
   // @Test
    public void tc05_click_and_select_element(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> listnumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        System.out.println("Tong so luong number= " + listnumber.size());

        //Click and select random : 1, 3, 5, 11
        action.keyDown(Keys.CONTROL).perform();
        action.click(listnumber.get(0))
                .click(listnumber.get(2))
                .click(listnumber.get(4))
                .click(listnumber.get(10)).perform();
        sleepInSecond(2);

        //release the ctrl key
        action.keyDown(Keys.CONTROL).perform();

        List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        System.out.println("Tong so luong number duoc chon= " + listNumberSelected.size());


        //Verify selected number
        Assert.assertEquals(listNumberSelected.size(), 4);

        //Verify selected text

    }

    //@Test
    public void tc06_double_click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //double click
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
    }

    @Test
    public void tc07_double_click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //double click
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
    }




    public void  sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
