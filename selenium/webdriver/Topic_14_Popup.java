    package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_14_Popup {
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01_fixed_popup(){
        driver.get("https://ngoaingu24h.vn/");
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());

        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
        sleepInSecond(3);

        //Verify show popup Login
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());

        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("button.btn-v1.btn-login-v1.buttonLoading")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(),"Tài khoản không tồn tại!");

        //close popup
        driver.findElement(By.xpath("//h4[text()='Đăng nhập']/preceding-sibling::button")).click();
        sleepInSecond(3);
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());

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
