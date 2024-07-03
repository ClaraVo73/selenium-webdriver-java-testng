package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_17_Window {
    WebDriver driver;
    WebDriverWait explicitWait;
    Alert alert;
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01_title(){
        //parent page
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //1. Click on google > switch to window tab > Verify title of new window
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(2);

        switchToWindowByPageTitle("Google");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");
        Assert.assertEquals(driver.getTitle(),"Google");

        //3. Switch to parent window
        switchToWindowByPageTitle("Selenium WebDriver");

        //4. Click on facebook link > switch to new tab >> Verify title of facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(2);

        switchToWindowByPageTitle("Facebook – log in or sign up");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");

        //5. Switch to parent window
        switchToWindowByPageTitle("Selenium WebDriver");

        //4. Click on tiki link > switch to new tab >> Verify title of tiki
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSecond(2);

        switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getCurrentUrl(),"https://tiki.vn/");
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        //5. CLose all window except parent window
        closeWindowsByTitle("Selenium WebDriver");
        switchToWindowByPageTitle("Selenium WebDriver");


    }

    @Test
    public void tc02_id_window(){
        //parent page
        driver.get("http://live.techpanda.org/");
        String parentID = driver.getWindowHandle();
        System.out.println(parentID);
        //1. CLick on Mobile tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSecond(2);

        //2. Add to compare of Sony Xperia and Samsung Galaxy >> Click to compare
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        sleepInSecond(3);

        //3. Switch to new window >> Verify title of window >> Close tab
        switchToWindowByID(parentID);
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        String compareID = driver.getWindowHandle();

        //4. Switch to parent window >> Clear all > accept alert >> Verify message
        closeWindowByID(parentID);
       // switchToWindowByID(compareID);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");

    }

    @Test
    public void tc03_title_cambridge(){
        //parent page
        driver.get("https://dictionary.cambridge.org/vi/");
        String parentID = driver.getWindowHandle();

        //1.Click on Sign in
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();

        //2. Switch to new window ==> click on Log in
        switchToWindowByPageTitle("Login");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        //3. Verify message
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username' and @placeholder='Email *']/following-sibling::span")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password *']/following-sibling::span")).getText(),"This field is required");

        //4. Close login window and switch to parent window
        closeWindowByID(parentID);

        //5. Input key search and click on Search button
        driver.findElement(By.id("searchword")).sendKeys("baby");
        driver.findElement(By.cssSelector("button[title='Tìm kiếm']")).click();

        //6. Verify search page have key search
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='entry']//span[text()='baby']")).isDisplayed());
    }

   //Case fail
  // @Test
    public void tc04_title_cambridge(){
        //parent page
        driver.get("https://courses.dce.harvard.edu/");
        String parentID = driver.getWindowHandle();

        //1. CLick on student login
        driver.findElement(By.cssSelector("a[data-action='login']")).click();

        //2. Switch to new window and verify Login portal
        switchToWindowByPageTitle("Harvard Division of Continuing Education Login Portal");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@title='Harvard DCE']/ancestor::div[@class='ce7b58616']")).isDisplayed());

        //3.close and switch parent window
        closeWindowByID(parentID);

        //4. Verify show authentication and close popup
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Authentication was not successful.  Please try again.']")).isDisplayed());
        driver.findElement(By.xpath("//button[contains(@class,'sam-wait__close')]")).click();

        //5. Input keyword
        driver.findElement(By.id("crit-keyword")).sendKeys("math");


    }


    public void  sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // Dung cho duy nhat 2 ID (Window/tab)
    public void switchToWindowByID(String otherID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            if (!id.equals(otherID)){
                driver.switchTo().window(id);
                sleepInSecond(2);
            }

        }

    }

    // Dung cho nhieu hon 2 ID (Window/tab)
    public void switchToWindowByPageTitle(String expectedPageTitle){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            //Switch tung ID truoc
            driver.switchTo().window(id);

            //Lay ra title cua page nay
            String actualPageTitle = driver.getTitle();

            if (actualPageTitle.equals(expectedPageTitle)){
                break;
            }

        }

    }
    //should not used
    public void closeWindowsByTitle(String expectedPageTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualPageTitle = driver.getTitle();

            // Kiểm tra nếu title của page hiện tại trùng với expectedPageTitle
            if (actualPageTitle.equals(expectedPageTitle)) {
                // Không làm gì cả, vì đây là tab hiện tại
            } else {
                    driver.close();
                }
            }

        }

    //should use
    public void closeWindowByID(String parentID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            if (!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
                sleepInSecond(2);
            }

        }
        driver.switchTo().window(parentID);
    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
