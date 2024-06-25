package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Custom_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
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
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01(){
        driver.get("https://login.ubuntu.com/");
        sleepInSecond(2);

        //Click on the radio button: I don’t have an Ubuntu One account
        //CLick on checkbox: I have read and accept
        By radioButton = By.xpath("//label[contains(string(),\"I don’t have\")]/preceding-sibling::input");
        By checkbox = By.xpath("//label[contains(text(),'I have read and accept')]/preceding-sibling::input");
        jsExecutor.executeScript("arguments[0].click(); arguments[1].click()", driver.findElement(radioButton), driver.findElement(checkbox));
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(radioButton).isSelected());
        Assert.assertTrue(driver.findElement(checkbox).isSelected());


       // driver.findElement(By.xpath("//label[contains(text(),\"I have read and accept\")]/preceding-sibling::input")).click(); => khong click duoc

    }
    @Test
    public void tc02(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        sleepInSecond(2);

        //Check radio Can Tho is not selected by isDisplayed()
        WebElement radioCanTho = driver.findElement(By.xpath("//span[text()='Cần Thơ']/ancestor::div[@class='YEVVod']/preceding-sibling::div"));
        if (!radioCanTho.isDisplayed()){
            //Select radio Can Tho
            radioCanTho.click();
            Assert.assertTrue(radioCanTho.isDisplayed());
        }   Assert.assertTrue(radioCanTho.isDisplayed());
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
