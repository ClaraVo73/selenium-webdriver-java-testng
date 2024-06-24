package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Button {
    WebDriver driver;
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

  //  @Test
    public void tc01_button(){
        //1. Truy cap trang
        driver.get("https://www.fahasa.com/customer/account/create");
        //2. Navigate qua tab dang nhap
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");
        //3. Verify "Dang nhap" button la disable
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        //4. Verify "Dang nhap" button co background la mau xam
        String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
        System.out.println(loginButtonBackground);
        Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));

        //5.Input du lieu hop le vao email, mat khau textbox
        driver.findElement(By.id("login_username")).sendKeys("0908741221");
        driver.findElement(By.id("login_password")).sendKeys("abcD123#");
        sleepInSecond(2);

        //6. Verify "Dang nhap" button la enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        //7. Verify" Dang nhap" button co background la mau do
       // String expectedColor = "rgba(201, 33, 39, 1)";
        String hexColor = "#C92127";
        // Chuyển đổi màu từ hex sang rgba
        String rgbaColor = hexToRgba(hexColor);
        // In ra màu rgba đã chuyển đổi
        System.out.println(rgbaColor);
        // Kiểm tra xem màu rgba đã chuyển đổi có tồn tại trong thuộc tính background-color của nút đăng nhập hay không
        Assert.assertEquals(driver.findElement(loginButton).getCssValue("background-color"),rgbaColor);

    }

    @Test
    public void tc02_checkbox_radiobutton(){
        //1. Truy cap trang
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSecond(10);
        //2. Click vao checkbox Dual-zone air conditioning va Kiem tra checkbox do da chọn
        checkToCheckBox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());


        //4. Bo chon checkbox va kiem tra no chua duoc chọn
        unCheckToCheckBox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        //5. Truy cap vao trang
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        //6. Click vao radio button: 2.0 Petrol, 147kW
        checkToCheckBox(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::span/input"));
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::span/input")).isSelected());

    }

    public void checkToCheckBox(By by){
        if (!driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    public void unCheckToCheckBox(By by){
        if (driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    @Test
    public void tc03_checkbox_multiple(){
        //1. Truy cap trang
        driver.get("https://automationfc.github.io/multiple-fields/");
        //2. Chon het tat ca checkbox o man hinh nay
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));

        for (WebElement checkbox : allCheckboxes){
            checkbox.click();
            sleepInSecond(1);
        }

        //3. Kiem tra het tat ca checkbox do da chon

        for (WebElement checkbox : allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }
        //4. Chi chon mot checkbox trong tat ca cac checkbox
        for (WebElement checkbox : allCheckboxes){
            if (checkbox.getAttribute("value").equals("Cancer")){
                checkbox.click();
            }
        }
        //5. Kiem tra duy nhat checkbox nay da chon


    }
    public void  sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static String hexToRgba(String hexColor) {
        Color color = Color.decode(hexColor);
        return String.format("rgba(%d, %d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue(), 1);
    }

    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}
