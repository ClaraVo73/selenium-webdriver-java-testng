package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Dropdown_List {
    WebDriver driver;
    Select select;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String firstName, lastName, email, password, day, month, year;
    String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        firstName = "Caaa";
        lastName = "Nuuu";
        email = "Caaa" + getRandomNumber() + "@gmail.com";
        password = "Wee1234@";
        day = "1";
        month = "May";
        year = "1980";
        countryName = "United States";
        provinceName = "Alaska";
        cityName = "Anchorage";
        addressName = "134 HCM";
        postalCode = "1700";
        phoneNumber = "+6413425466";
    }

    @Test
    public void tc01(){
//Register new account
//1. Access page
        driver.get("https://demo.nopcommerce.com/register");
//2. Click Register on Header
        driver.findElement(By.cssSelector(".ico-register")).click();
//3. Input form
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
// Select day, month, year
        new Select((driver.findElement(By.name("DateOfBirthDay")))).selectByVisibleText(day);
        new Select((driver.findElement(By.name("DateOfBirthMonth")))).selectByVisibleText(month);
        new Select((driver.findElement(By.name("DateOfBirthYear")))).selectByVisibleText(year);
//4. Click Register button
        driver.findElement(By.id("register-button")).click();
//5. Verify go to Home page after registration completed
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
//6. Click on My account
        driver.findElement(By.cssSelector("a.ico-account")).click();
//7. Verify day/month/year
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
//Check dropdown is not multiple
//       Assert.assertFalse(new Select(driver.findElement(By.name("DateOfBirthDay"))).isMultiple());
    }
    @Test
    public void tc02(){
//Add new
//1. Click on Address
        driver.findElement(By.xpath("//div[@class='side-2']//a[text()='Addresses']")).click();
//2. Click Add new
        driver.findElement(By.xpath("//button[@class='button-1 add-address-button']")).click();
//3. Input My account
        driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
        driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
        driver.findElement(By.id("Address_Email")).sendKeys(email);
        new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
        new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
        driver.findElement(By.id("Address_City")).sendKeys(cityName);
        driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
        driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
        driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
// Click Save button
        driver.findElement(By.cssSelector("button.save-address-button")).click();
//4. Verify account
        Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(),firstName+ " " + lastName);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(email));
        Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
        //Assert.assertEquals(driver.findElement(By.cssSelector("li.email")).getText(),email);
        //Assert.assertEquals(driver.findElement(By.cssSelector("li.phone")).getText(),phoneNumber);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),countryName);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.stateprovince")).getText(),provinceName);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.city")).getText(),cityName);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(),addressName);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.zippostalcode")).getText(),postalCode);

    }
    public int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(99999);
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
