package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_16_Iframe {
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
    public void tc01_iframe_toidicodedao(){
        driver.get("https://toidicodedao.com/");

        //1.Verify facebook frame hien thi
        driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com')]")).isDisplayed();

        //2. Verify so luong like cua facebook la xxxx folower
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com')]")));
        String facebookFollower = driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[contains(text(),'followers')]")).getText();
        System.out.println("Facebook Follower: " + facebookFollower);

        Assert.assertEquals(facebookFollower, "406,263 followers");
// Lỗi  No such element Exeption  cua Frame/Iframe xay ra do 3 case sau:
        //1. Dung tu main page maf chua switch qua iframe da di tim element thuoc iframe do
        //2. Dung o iframe 1 nhung lai thao tac voi element cua iframe 2
        //3. Dung o iframe 1 nhung lai thao ta voi element cua main page
            //switch tu iframe 1 qua main page
            //driver.switchTo().defaultContent();
            // Tu main page switch qua iframe 2
            // driver.switchTo().frame(driver.findElement(By.)));


    }

    @Test
    public void tc02_iframe_formsite(){
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        //1.Input year/ residence / gender and submit
        driver.findElement(By.id("imageTemplateContainer")).click();
        sleepInSecond(2);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'showFormEmbed')]")));
        new Select(driver.findElement(By.id("RESULT_RadioButton-2"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.id("RESULT_RadioButton-3"))).selectByVisibleText("East Dorm");
        driver.findElement(By.xpath("//label[text()='Female']")).click();
        sleepInSecond(2);
        driver.findElement(By.id("FSsubmit")).click();

        //2. Click on login button
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//nav[contains(@class,'header--desktop-floater')]//a[@title='Log in']")).click();

        //3. CLick on Login  on Login page
        driver.findElement(By.id("login")).click();

        //4. Verify show error message
        Assert.assertTrue(driver.findElement(By.id("message-error")).isDisplayed());


    }

    @Test
    public void tc03_frame(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        //1.Input CustomerID and click Continue
        driver.switchTo().frame(driver.findElement(By.name("login_page")));
        driver.findElement(By.name("fldLoginUserId")).sendKeys("Caa1111");
        driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
        sleepInSecond(3);
        //2. Verify show Password textbox
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());


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
