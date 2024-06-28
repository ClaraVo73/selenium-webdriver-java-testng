package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Random_Popup {
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

  //  @Test
    public void tc01_edu(){
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(10);

        if (driver.findElement(By.xpath("//div[@id='pum-23751']")).isDisplayed()){
            driver.findElement(By.xpath("//div[@id='pum-23751']//button[@class='pum-close popmake-close']")).click();
            sleepInSecond(3);

            driver.findElement(By.xpath("//div[@id='mega-menu-wrap-primary']//a[text()='Liên hệ']")).click();
        } driver.findElement(By.xpath("//div[@id='mega-menu-wrap-primary']//a[text()='Liên hệ']")).click();

    }

    @Test
    public void tc02_dehieu(){
        driver.get("https://dehieu.vn/");
        sleepInSecond(10);

        WebElement popup = driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']"));
        //1. Show popup and input email then click close popup
        //2. Don't show popup
        if (popup.isDisplayed()){
            System.out.println("Go to popup");
            //Input email , name and sign up
            driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']//input[@name='fields[full_name]']")).sendKeys("Clara");
            driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']//input[@name='fields[email]']")).sendKeys(getRandomEmailAddress());
            sleepInSecond(3);
            driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']//button[@class='btn config-button']")).click();
            sleepInSecond(3);

            //Verify dang ky thanh cong
            Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Bạn đã đăng ký thành công']")).isDisplayed());
            driver.findElement(By.xpath("//div[@class='modal-content css-modal-bt']//button[@class='close']")).click();
            sleepInSecond(3);
        }else {
            System.out.println("Do not show popup");

        }
        Assert.assertFalse(popup.isDisplayed());
        //3. click  on dang nhap
        driver.findElement(By.xpath("//a[text()=' Đăng nhập']")).click();


    }


    public void  sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public String getRandomEmailAddress(){
        Random rand = new Random();
        return "clara" + rand.nextInt(99999) + "@gmail.com";
    }
    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}
