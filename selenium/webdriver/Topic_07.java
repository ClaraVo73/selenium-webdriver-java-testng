package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07 {
    WebDriver driver;
    Random rand = new Random();
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String employeeID = String.valueOf(rand.nextInt(99999));
    String passportNumber = "7891-123-48792";
    String commentInput = "I just update my passpost number\nThank you";

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }
        rand = new Random();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC01_Create_New_Employee(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        sleepInSecond(3);
//Click on PIM menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        sleepInSecond(2);
//Click on Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        sleepInSecond(2);
//Add employee
        driver.findElement(By.name("firstName")).sendKeys("Automationfc");
        driver.findElement(By.name("lastName")).sendKeys("test");
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
        sleepInSecond(2);
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
        driver.findElement(By.cssSelector(".oxd-switch-input")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("abd"+employeeID);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Ttt1779#");
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Ttt1779#");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSecond(5);
//Trang dung tech JS Framwork: VueJS/ Angular/ React thi value no se khong nam trong HTML
//Verify firstname, lastname and employeeID
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automationfc");
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"test");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
//Go to Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        sleepInSecond(5);
//Click Add button
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
//Input passport number and comment
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentInput);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        sleepInSecond(6);
//Click on Edit
        driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
        sleepInSecond(2);
//Verify passport number and comment show exactly
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),commentInput);
//Log out
        driver.findElement(By.cssSelector(".oxd-userdropdown-tab")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        sleepInSecond(3);
//login again
        driver.findElement(By.name("username")).sendKeys("abd"+employeeID);
        driver.findElement(By.name("password")).sendKeys("Ttt1779#");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        sleepInSecond(3);
//Go to Info page
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
//Verify firstname, lastname, employeeID
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automationfc");
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"test");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
//Go to Immigration > click on pencil
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        sleepInSecond(5);
        driver.findElement(By.cssSelector(".bi-pencil-fill")).click();
        sleepInSecond(2);
//Verify number and comments show exactly
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),commentInput);
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
