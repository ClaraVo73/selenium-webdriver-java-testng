package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Element_Condition_Status {
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void tc01_visible_displayed_visibility(){
        driver.get("https://www.facebook.com/");
        //1. Co tren UI (bat buoc)
        //1. Co trong HTML (bat buoc)
        
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }

    @Test
    public void tc02_invisible_undisplayed_invisibility(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        //1. Khong co tren UI (bat buoc)
        //1. Co trong HTML
        // cho cho Re-enter email textbox khong hien thi trong vong 10s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
    }

    @Test
    public void tc03_presence_I(){
        driver.get("https://www.facebook.com/");

        //1. Co tren UI
        //1. Co trong HTML (bat buoc)

        // cho cho email address presence trong HTML trong vong 10s
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }
    @Test
    public void tc03_presence_II(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        //1. Khong co tren UI
        //1. Co trong HTML (bat buoc)

        // cho cho Re-enter email textbox presence trong HTML (case naykhong hien thi tren UI ) trong vong 10s
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
    }

    @Test
    public void tc04_staleness(){
        //1. Khong co tren UI
        //1. Khong co trong HTML
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        //1. element co trong HTML
        WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
        //2. Thao tac voi element khac lam cho element re-enter email khong con trong DOM nua
        //3..4..5..
        //6. Close popup di
        driver.findElement(By.cssSelector("img._8idr")).click();

        // cho cho Re-enter email textbox khong con trong DOM trong vong 10s
        explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
