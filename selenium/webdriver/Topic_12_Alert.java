package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_12_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    Alert alert;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01_accept_alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //method1: alert = driver.switchTo().alert();
        //method2:
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //Verify show message in Alert
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        //Accept alert
        alert.accept();

        //Verify show message:You clicked an alert successfully
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");

    }
    @Test
    public void tc02_confirm_alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //Verify show message in Alert
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        //Cancel alert
        alert.dismiss();

        //Verify show message:You clicked an alert successfully
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void tc03_prompt_alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //Verify show message in Alert
        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        //Enter in textbox
        String courseName = "automationfc yeah yeah";
        alert.sendKeys(courseName);
        sleepInSecond(2);

        alert.accept();
        sleepInSecond(1);

        //Verify show message:You entered: automationfc yeah yeah
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: " + courseName);
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
