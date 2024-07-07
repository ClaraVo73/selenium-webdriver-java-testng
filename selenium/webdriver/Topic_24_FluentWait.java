package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_24_FluentWait {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExcutor;
    FluentWait<WebDriver> fluentDriver;

    long allTime = 15;
    long pollingTime = 100;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void tc01(){
        driver.get("https://automationfc.github.io/fluent-wait/");
        Assert.assertTrue(findElement("//div[@id='javascript_countdown_time']").isDisplayed());

    }
    @Test
    public void tc02(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement("//div[@id='start']").click();
        Assert.assertTrue(findElement("//div[@id='start']").isDisplayed());

    }
    public WebElement findElement (String xpathLocator){
        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
        return  fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.xpath(xpathLocator));
            }
        });
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
