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

   // @Test
    public void tc01_fixed_popup_ngoaingu(){
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

    //@Test
    public void tc02_fixed_popup_kyna(){
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        //Verify show popup Login
        Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sai tên đăng nhập hoặc mật khẩu']")).isDisplayed());
    }

    //@Test
    public void tc03_fixed_popup_tiki(){
        driver.get("https://tiki.vn/");
        sleepInSecond(5);

        //1 CLose ad
        driver.findElement(By.xpath("//img[@alt='close-icon']")).click();

        //2 Verify not show popup Login
        Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(),0);

        //3 Click on Tai khoan and verify popup is shown
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div[role='dialog']")).isDisplayed());

        //4 Click on Dang nhap bang email link
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSecond(1);

        //5  No input and click on Dang nhap
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSecond(2);

        //6 Verify show text
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());

        //7 Click on close popup
        driver.findElement(By.cssSelector("img.close-img")).click();

        //8 Verify not show popup Login
        Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(),0);

    }

    //@Test
    public void tc04_fixed_popup_facebook(){
        driver.get("https://www.facebook.com/");
        sleepInSecond(5);

        //2 Verify not show popup sign up
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']//ancestor::div[@class='_n8 _3qx _8idq _8esf _8f3m _8fgk uiLayer _3qw']")).size(),0);

        //1. Click new account button
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        sleepInSecond(3);

        //2 Verify show popup Login
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']//ancestor::div[@class='_n8 _3qx _8idq _8esf _8f3m _8fgk uiLayer _3qw']")).size(),1);

        //3 Click on close popup
        driver.findElement(By.xpath("//div[text()='Sign Up']//ancestor::div[@class='_8ien']/img")).click();
        sleepInSecond(3);

        //8 Verify not show popup Login
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']//ancestor::div[@class='_n8 _3qx _8idq _8esf _8f3m _8fgk uiLayer _3qw']")).size(),0);

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
        //driver.quit();
    }
}
