package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait linh dong, neu chua tim thay element thi cho tiep. Neu thay thi khong cho nua
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void tc01_jquery(){
//1.Go to link
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
//2.Click on "Select a speed" dropdown
        driver.findElement(By.id("speed-button")).click();
//3.Wait all item are loaded successfully
//Locator phai lay de dai dien cho all items
// Lay den the chua text
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
// Dua tat ca cac item trong dropdown vao list
        List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
//3.1 Find item need use (dung vong lap duyet qua de tim)
        for (WebElement tempItem : speedDropdownItems){
            String itemText = tempItem.getText();
            System.out.print(itemText);
//4. Check expected text
            if (itemText.equals("Faster")){
//5. Click on this item
                tempItem.click();
            }
// Thoat ra khoi vong lap
            break;
        }
//3.2 No need scroll down to find item


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
