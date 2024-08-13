package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Web_Driver_Methods_Quiz {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name").toLowerCase();
    Actions action;
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void dropdown() {
/**
 * Go here "https://the-internet.herokuapp.com/dropdown"
 * Select option 1 from the dropdown
 * Assert that option 1 is selected
 * Assert taht option 2 is NOT selected
 * */
    driver.get("https://the-internet.herokuapp.com/dropdown");
    element = driver.findElement(By.id("dwopdown"));
    element.click();
    WebElement option1 = element.findElement(By.cssSelector("option[value='1']"));
    WebElement option2 = element.findElement(By.cssSelector("option[value='2']"));
    option1.click();
    Assert.assertTrue(option1.isDisplayed());
    Assert.assertFalse(option2.isDisplayed());
    }
    @Test
    public void hover() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        /*
         * Go to "https://the-internet.herokuapp.com/hovers"
         * Hover over the first image
         * Assert that on hover there is text that is displayed below "name: user1"
         * */
        element = driver.findElement(By.className("figure"));
        action.moveToElement(element).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='name: user1']")).isDisplayed());

    }

    public void context_menu() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        /*
         * https://the-internet.herokuapp.com/context_menu
         * Right click
         * close alert
         * driver.switchTo().alert().accept();
         * */
    }

    public void presses() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        // go to "https://the-internet.herokuapp.com/key_presses"
        // send right arrow key to the input box
        // assert that you got this text back "You entered: RIGHT"
    }

    public void simple_html() {
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        // go to https://ultimateqa.com/simple-html-elements-for-automation/
        // find element with text "Clickable Icon"
        // Assert href attribute =  https://ultimateqa.com/link-success/
        // Get CSS value: "background-origin"
        // Assert that it equals "padding-box"
    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
