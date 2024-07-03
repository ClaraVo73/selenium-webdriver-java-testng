package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_19_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void tc01_by_sendkeys(){
        //1. access page
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //2. Upload 3 files
        //3. Check upload file successfully
        //4. CLick on Start button of files
        //5. Verify successful upload 3 files

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
