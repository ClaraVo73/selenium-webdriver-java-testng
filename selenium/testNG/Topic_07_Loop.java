package testNG;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_07_Loop {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(invocationCount = 3)
    public void Register()  {
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");

        driver.findElement(By.id("firstname")).sendKeys("papa");
        driver.findElement(By.id("middlename")).sendKeys("mama");
        driver.findElement(By.id("lastname")).sendKeys("son");

        String emailAddress = "papa" + getRandomNumber() + "@gmail.com";
        System.out.println(emailAddress);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys("987987");
        driver.findElement(By.id("confirmation")).sendKeys("987987");
        driver.findElement(By.cssSelector("button[title='Register']")).click();

//        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("papa mama"));

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();

    }

    public int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(99999);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
