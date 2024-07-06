package webdriver;

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

public class Topic_20_ExplicitWait {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
       // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    public void tc01_not_enough_time(){
       driver.get("https://automationfc.github.io/dynamic-loading/");
       explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
       driver.findElement(By.cssSelector("div#start>button")).click();

       // thieu thoi gian cho hien thi element

       explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

       //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void tc02_enough_time(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // du thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void tc03_more_time(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // thua thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void tc04_visible(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        // thua thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    @Test
    public void tc05_invisible(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        // thua thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
