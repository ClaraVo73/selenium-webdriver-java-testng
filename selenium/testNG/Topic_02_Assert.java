package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
    WebDriver driver;

    @Test
    public void TC_01()

    {
        //3 ham assert hay dung
        // Kiem tra tinh dung dan cua du lieu
        //1. Kiem tra du lieu minh mong muon la DUng
        // Email textbox hien thi
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());

        //2. Kiem tra du lieu minh mong muon la  sai
        //Email textbox khong hien thi
        Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());

        //3. Kiem tra du lieu minh mong muon voi du lieu thuc te la bang nhau

        // Tuyet doi 2 cai bang nhau
        Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
        Assert.assertEquals(driver.findElement(By.id("advice-requird-entry-email")).getText(), "This is a required field.");

        //Tuong doi
        String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
        Assert.assertTrue(benefitText.contains("Faster checkout"));
        Assert.assertTrue(benefitText.contains("Save multiple shipping addresses"));
        Assert.assertTrue(benefitText.contains("View and track orders and more"));
    }
}
