package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_StaticWait {
    WebDriver driver;
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
        driver.manage().window().maximize();
    }

    @Test
    public void tc01_not_enough_time(){
       driver.get("https://automationfc.github.io/dynamic-loading/");
       driver.findElement(By.cssSelector("div#start>button")).click();

       sleepInSecond(3);
       //Fix cung wait thuong apply khi thu nghiem case/ implement test case - Khong dung bua bai
        // Su dung voi Window/Tab khi wait cho page moi load thanh cong
        // Upload multiple file - can sleep cung sau moi lan upload file


       //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void tc02_enough_time(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        sleepInSecond(5);
        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void tc03_more_time(){

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        sleepInSecond(10);
        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void  sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
