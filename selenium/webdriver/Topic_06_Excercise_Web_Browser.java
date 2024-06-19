package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Excercise_Web_Browser {
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01(){
        driver.get("http://live.techpanda.org/");

        //login page
        driver.findElement(By.cssSelector("div[class='footer'] [title='My Account']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        //Create an Account page
        driver.findElement((By.cssSelector("a[title='Create an Account']"))).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

    }
    @Test
    public void tc02(){
        driver.get("http://live.techpanda.org/");

        //login page
        driver.findElement(By.cssSelector("div[class='footer'] [title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");

        //Create an Account page
        driver.findElement((By.cssSelector("a[title='Create an Account']"))).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void tc03(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] [title='My Account']")).click();
        driver.findElement((By.cssSelector("a[title='Create an Account']"))).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void tc04(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] [title='My Account']")).click();
        sleepInSecond(2);
        //Verify page HTML co chua 1 chuoi mong muon
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement((By.cssSelector("a[title='Create an Account']"))).click();
        sleepInSecond(2);
        //Verify page HTML co chua 1 chuoi mong muon
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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
