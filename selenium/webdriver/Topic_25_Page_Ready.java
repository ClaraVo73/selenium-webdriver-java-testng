package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Topic_25_Page_Ready {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;

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
    }

   // @Test
    public void tc01_nopcommerce(){
        driver.get("https://admin-demo.nopcommerce.com");
        Assert.assertTrue(isPageLoadedSuccess());
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.button-1.login-button")).click();

        Assert.assertTrue(isPageLoadedSuccess());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.content-header h1")).getText(), "Dashboard");

        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(isPageLoadedSuccess());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title strong")).getText(), "Welcome, please sign in!");
    }

    @Test
    public void tc02_tricentis(){
        driver.get("https://www.tricentis.com/learn");

        String keyWord = "Selenium";

        //action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'HeaderTopMenu_top-menu--desktop__jlBBG')]//button"))).perform();
        action.click(driver.findElement(By.xpath("//div[contains(@class,'HeaderTopMenu_top-menu--desktop__jlBBG')]//button"))).perform();
        //  driver.findElement(By.xpath("//div[contains(@class,'HeaderTopMenu_top-menu--desktop__jlBBG')]//button")).click();

        Assert.assertTrue(isPageLoadedSuccess());
        action.sendKeys( driver.findElement(By.xpath("//div[contains(@class,'Background_content')]//input")), keyWord);
        //driver.findElement(By.xpath("//div[contains(@class,'Background_content')]//input")).sendKeys(keyWord)
        //driver.findElement(By.xpath("//div[contains(@class,'Background_content')]//button[@type='submit']")).click();
                action.click(driver.findElement(By.xpath("//div[contains(@class,'Background_content')]//button[@type='submit']")));

        Assert.assertTrue(isPageLoadedSuccess());

        List<WebElement> h2Elements = driver.findElements(By.xpath("//h2[@class='SearchResultItem_result-item__title__3sI2_ SearchResultItem_h4__0782H']"));
        List<WebElement> pElements = driver.findElements(By.xpath("//span[@class='excerpt_part']"));

        boolean isKeywordFound = false;

        // Kiểm tra từ khóa trong các phần tử h2
        for (WebElement h2E : h2Elements) {
            String h2Text = h2E.getText();
            System.out.println("Checking h2 element: " + h2Text);
            if (h2Text.contains(keyWord)) {
                isKeywordFound = true;
                break;
            }
        }

        // Nếu không tìm thấy từ khóa trong các phần tử h2, kiểm tra trong các phần tử p
        if (!isKeywordFound) {
            for (WebElement pE : pElements) {
                String pText = pE.getText();
                System.out.println("Checking p element: " + pText);
                if (pText.contains(keyWord)) {
                    isKeywordFound = true;
                    break;
                }
            }
        }

        // Assert rằng từ khóa được tìm thấy ít nhất trong một trong các phần tử h2 hoặc p
        Assert.assertTrue(isKeywordFound, "Expected keyword '" + keyWord + "' to be found in <h2> or <p> elements.");
    }

    // Moi su kien chuyen trang thi goi ham nay => Cho page ready
    public boolean isPageLoadedSuccess() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(100));
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
