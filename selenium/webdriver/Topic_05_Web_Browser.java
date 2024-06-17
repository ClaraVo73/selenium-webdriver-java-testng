package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_05_Web_Browser {
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

        // Tuơng tac vs Browser thi se thong qua bien Webdriver driver
        // Tuơng tac vs Element thi se thong qua bien Webelement element
    }

    @Test
    public void tc01(){
        //**dong tab dang dung
        driver.close();

        //**dong trinh duyet
        driver.quit();

        // ** tim 1 element
        // co the luu vao mot bien de su dung choi cac step sau => dung lai nhieu lan
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email'"));
        emailTextbox.clear();
        emailTextbox.sendKeys("");

        //** co the su dung luon ( ko can tao bien)
        driver.findElement(By.xpath("//input[@id='button'")).click();

        //*tim nhieu element
        List<WebElement> checkboxes = driver.findElements(By.xpath(""));

        //Mo ra 1 url nao do
        driver.get("https://www.facebook.com/");

        //Click vao link tieng viet
        //Tra ve url cua page hien tai
        //Verify tuong doi
        Assert.assertEquals(driver.getPageSource().contains("Facebook giúp bạn kết nối"));

        //Tra ve title cua page hien tai
        Assert.assertEquals(driver.getTitle(), "Facebook-Dang nhap hoac Dang ky");

        //WebDriver API - Windows/tabs
        //**Lay ra duoc ID cua tab window ma drive dang dung (active)
        String loginWindowID = driver.getWindowHandle();

        //*Lay ra ID cua tat ca window/tab
        Set<String> allIDs = driver.getWindowHandles();

        //Cookie/ cache
        WebDriver.Options opt = driver.manage();

        //* Login thanh cong -> Luu lai
        opt.getCookies();

        opt.logs();

        WebDriver.Timeouts time = opt.timeouts();


        //Implicit wait and depend on : FindElement/FindElements
        //** Khoang thoi gian cho element xuat hien trong vong x giay
        time.implicitlyWait(5, TimeUnit.SECONDS);
        //5s = 5000 ms = 5000000 uSEC
        time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
        time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);

        //Khoang thoi gian cho page load xong trong vong x giay
        time.pageLoadTimeout(5, TimeUnit.SECONDS);

        //Webdriver API- Javascript Executor (JavascriptExecutor library)
        // Khoang thoi gian cho script duoc thuc thi xong trong vong x giay)
        time.setScriptTimeout(5, TimeUnit.SECONDS);

        WebDriver.Window win = opt.window();
        win.fullscreen();
        win.maximize(); //**

        //Test GUI: Functional
        //Test GUI: Font/ Size/ Color/ Position/ Location ...
        win.getPosition();
        win.getSize();

        WebDriver.Navigation nav = driver.navigate();
        nav.back();
        nav.refresh();
        nav.forward();
        nav.to("https://www.facebook.com/");

        WebDriver.TargetLocator tar = driver.switchTo();
        //* WebDriver API - Alert/ Authentication Alert ( Alert library)
        tar.alert();

        //* WebDriver API - Frame/Iframe (Frame library)
        tar.frame("");

        //* WebDriver API - Windows/Tabs
        tar.window("");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
