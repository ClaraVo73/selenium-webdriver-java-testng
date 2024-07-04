package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_19_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;


    String beachFile = "beach.jpg";
    String sunFile = "sun.jpg";
    String townFile = "town.jpg";

    String beachFilePath = projectPath + "\\uploadFiles\\" + beachFile;
    String sunFilePath = projectPath + "\\uploadFiles\\" + sunFile;
    String townFilePath = projectPath + "\\uploadFiles\\" + townFile;
    //E:/21. Selenium/5.Intellij-selenium-projects/selenium-webdriver-java-testng/uploadFiles/beach.jpg

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01_by_sendkeys(){
        //1. access page
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //2. Upload 3 files
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(beachFilePath + "\n" + sunFilePath + "\n" + townFilePath);
        sleepInSecond(1);

        //3. Check upload file successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ beachFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ sunFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ townFile + "']")).isDisplayed());

        //4. CLick on Start button of files
        List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement button : buttonUpload){
            button.click();
            sleepInSecond(3);
        }

        //5. Verify successful upload 3 files by link
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ sunFile +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ beachFile +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ townFile +"']")).isDisplayed());

        //6. Verify successful upload 3 files by images
        Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + sunFile +"')]"));
        Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFile +"')]"));
        Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + townFile +"')]"));



    }
    public void  sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public boolean isImageLoaded(String locator) {
        WebElement element = getElement(locator);
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
        return status;
    }
    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));  // Adjust this based on your locator strategy
    }
    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}
