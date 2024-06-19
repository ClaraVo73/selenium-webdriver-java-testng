package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.reporters.XMLConstants;

import java.util.concurrent.TimeUnit;

public class Topic_06_Web_Element {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    By emailTextbox = By.id("mail");
    By ageUnder18Radio = By.id("under_18");
    By educationTextArea = By.id("edu");
    By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
    By jobRole1Textbox = By.id("job1");
    By developmentCheckbox = By.id("development");
    By slide01 = By.id("slider-1");
    By password = By.id("disable_password");
    By radioButtonDisabled = By.id("radio-disabled");
    By biography = By.id("bio");
    By jobRole03 = By.id("job3");
    By slide02 = By.id("slider-2");
    By javaLanguageCheckbox = By.id("java");



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

  //  @Test
    public void tc01_displayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Textbox- Neu co hien thi thi nhap text va in ra
        if (driver.findElement(emailTextbox).isDisplayed()){
            driver.findElement(emailTextbox).sendKeys("aaa@gmail.com");
            System.out.println("Email testbox is displayed");
        }else {
            System.out.println("Email is not displayed");
        }

        //Radio-
        if (driver.findElement(ageUnder18Radio).isDisplayed()){
            driver.findElement(ageUnder18Radio).click();
            System.out.println("Age Unser18 is displayed");
        }else {
            System.out.println("Age Unser18 is not displayed");
        }

        //Textarea-
        if (driver.findElement(educationTextArea).isDisplayed()){
            driver.findElement(educationTextArea).sendKeys("Duy Tan university");
            System.out.println("Education textarea is displayed");
        }else {
            System.out.println("Education textarea displayed");
        }

        //Text
        if (driver.findElement(nameUser5Text).isDisplayed()){
            System.out.println("Name User 5 is displayed");
        }else {
            System.out.println("Name User 5 is not displayed");
        }
    }

  //  @Test
    public void tc02_enabled(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Textbox- Neu co hien thi thi nhap text va in ra
        if (driver.findElement(emailTextbox).isEnabled()){
            System.out.println("Email testbox is enabled");
        }else {
            System.out.println("Email is disabled");
        }

        if (driver.findElement(ageUnder18Radio).isEnabled()){
            System.out.println("Age Under 18 radio is enabled");
        }else {
            System.out.println("Age Under 18 radio is disabled");
        }

        if (driver.findElement(educationTextArea).isEnabled()){
            System.out.println("Education is enabled");
        }else {
            System.out.println("Education is disabled");
        }

        if (driver.findElement(jobRole1Textbox).isEnabled()){
            System.out.println("Job Role 01 textbox is enabled");
        }else {
            System.out.println("Job Role 01 textbox  is disabled");
        }

        if (driver.findElement(developmentCheckbox).isEnabled()){
            System.out.println("Development checkbox is enabled");
        }else {
            System.out.println("Development checkbox  is disabled");
        }

        if (driver.findElement(slide01).isEnabled()){
            System.out.println("Slide 01 is enabled");
        }else {
            System.out.println("Slide 01  is disabled");
        }

        if (driver.findElement(password).isEnabled()){
            System.out.println("Password textbox is enabled");
        }else {
            System.out.println("Password textbox is disabled");
        }

        if (driver.findElement(radioButtonDisabled).isEnabled()){
            System.out.println("Age radio button is enabled");
        }else {
            System.out.println("Age radio button is disabled");
        }

        if (driver.findElement(biography).isEnabled()){
            System.out.println("Biography is enabled");
        }else {
            System.out.println("Biography is disabled");
        }

        if (driver.findElement(jobRole03).isEnabled()){
            System.out.println("Job role 03 is enabled");
        }else {
            System.out.println("Job role 03 is disabled");
        }

        if (driver.findElement(slide02).isEnabled()){
            System.out.println("Slide 02 is enabled");
        }else {
            System.out.println("Slide 02 is disabled");
        }



    }

   // @Test
    public void tc03_selected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Kiem tra element chua duoc select
        Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
        Assert.assertFalse(driver.findElement(javaLanguageCheckbox).isSelected());

        //Click
        driver.findElement(ageUnder18Radio).click();
        driver.findElement(javaLanguageCheckbox).click();
        sleepInSecond(2);

        //Kiem tra element da duoc select
        Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
        Assert.assertTrue(driver.findElement(javaLanguageCheckbox).isSelected());

    }
    @Test
    public void tc04_mailChimp(){
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.id("email")).sendKeys("ngan@gmail.com");

        By passwordMaiChimp = By.id("new_password");
        //By signUpButon = By.id("create-account-enabled");

        // lowercase character
        driver.findElement(passwordMaiChimp).sendKeys("abc");
        //driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // uppercase character
        driver.findElement(passwordMaiChimp).clear();
        driver.findElement(passwordMaiChimp).sendKeys("ABC");
       // driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        //number
        driver.findElement(passwordMaiChimp).clear();
        driver.findElement(passwordMaiChimp).sendKeys("12345");
        //driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // special character
        driver.findElement(passwordMaiChimp).clear();
        driver.findElement(passwordMaiChimp).sendKeys("@#$%^");
       // driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // 8 characters minimum
        driver.findElement(passwordMaiChimp).clear();
        driver.findElement(passwordMaiChimp).sendKeys("12345678");
        //driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // Must not contain username
        driver.findElement(passwordMaiChimp).clear();
        driver.findElement(passwordMaiChimp).sendKeys("Ngan123$");
        //driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

        // correct
        driver.findElement(passwordMaiChimp).clear();
        driver.findElement(passwordMaiChimp).sendKeys("Ha12345$");
        //driver.findElement(signUpButon).click();
        sleepInSecond(2);

        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

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
