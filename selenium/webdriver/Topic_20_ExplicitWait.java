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

    String beachFile = "beach.jpg";
    String sunFile = "sun.jpg";

    String beachFilePath = projectPath + "\\uploadFiles\\" + beachFile;
    String sunFilePath = projectPath + "\\uploadFiles\\" + sunFile;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //@Test
    public void tc01_not_enough_time(){
       driver.get("https://automationfc.github.io/dynamic-loading/");
       explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
       driver.findElement(By.cssSelector("div#start>button")).click();

       // thieu thoi gian cho hien thi element

       explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

       //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    //@Test
    public void tc02_enough_time(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // du thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }
    //@Test
    public void tc03_more_time(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // thua thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    //@Test
    public void tc04_visible(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        // thua thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    //@Test
    public void tc05_invisible(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        // thua thoi gian cho hien thi element
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        //Loading icon mat 5s moi bien mat
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

   // @Test
    public void tc06_ajax_loading(){

        //1. access page
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //2. wait until date picker shown on
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));

        //3. verify no selected date
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");

        //4. wait to date: 19 to be click able
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));

        //5. Click on date: 19
        driver.findElement(By.xpath("//a[text()='19']")).click();

        //6. Wait to invisible "loading" icon
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));

        //7.  wait to selected date: 19 to be click able back
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));

        //8. Verify selected date is 19
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Friday, July 19, 2024");
    }
    @Test
    public void tc07_ajax_loading(){
        //1. access page
        driver.get("https://gofile.io/welcome");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //2. Wait show upload button and Click it
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload Files']"))).click();

        //3. Wait show Addfile button and sendkey upload file
        //input#filesUploadInput
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add files']")));
        driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(beachFilePath + "\n" + sunFilePath);

        //4. Wait invisibility all progress bar
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@role='progressbar']/span"))));

        //5. Wait visibility text success
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div div"))).getText(), "Your files have been successfully uploaded");

        //7. Click on download link
        driver.findElement(By.xpath("//div[contains(@class, 'mainUploadSuccessLink')]//a")).click();

        //8. Check visibility download and play per file
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + sunFile + "']/parent::a/parent::div/parent::div//button[@class='btn btn-outline-secondary btn-sm p-1 text-white']")));
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + sunFile + "']/parent::a/parent::div/parent::div//button[@class='btn btn-outline-secondary btn-sm p-1 text-white']")).isDisplayed());

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFile + "']/parent::a/parent::div/parent::div//button[@class='btn btn-outline-secondary btn-sm p-1 text-white']")));
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + beachFile + "']/parent::a/parent::div/parent::div//button[@class='btn btn-outline-secondary btn-sm p-1 text-white']")).isDisplayed());
    }


    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}
