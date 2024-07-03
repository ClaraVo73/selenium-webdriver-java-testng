package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_18_Javascript_Execitor {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor)  driver;
    }

    @Test
    public void tc01(){
        //1. Access page
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSecond(5);

        //2. Get domain page
        Assert.assertEquals(executeForBrowser("return document.domain;"),"live.techpanda.org");

        //3. Get URL page
        Assert.assertEquals(executeForBrowser("return document.URL;"),"http://live.techpanda.org/");

        //4. Open Mobile page
        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(3);

        //5. Add products in cart
        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        sleepInSecond(3);

        //6. Verify show message
        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        //7. Open Customer service page and verify title
        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(3);

        //8. Scroll to element Newsletter textbox on bottom page
        hightlightElement("//span[text()='Newsletter']");
        scrollToElementOnTop("//span[text()='Newsletter']");
        sleepInSecond(3);

        //9. Input valid email in Newsletter textbox
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", "kkk" + getRandomNumber() + "@gmail.com");

        //10. Click on subscribe button
        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSecond(3);

        //11. Verify show text: Thank you for your subscription
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        //12. Navigate to domain: https://www.facebook.com/
        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSecond(5);

        Assert.assertEquals(executeForBrowser("return document.domain;"), "facebook.com");

    }

    @Test
    public void tc02_html5_validation_message(){
        //1. Access page
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
        sleepInSecond(5);

        String nameTextbox = "//input[@id='fname']";
        String passwordTextbox = "//input[@id='pass']";
        String emailTextbox = "//input[@id='em']";
        String addressTextbox = "//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select";
        String submitButton = "//input[@name='submit-btn']";

        //2. Click on submit - verify show message on Name textbox
        clickToElementByJS(submitButton);
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage(nameTextbox), "Please fill out this field.");
        //Input code on console of devtool to get message
        //var element = $x("//input[@id='pass']")[0];
        //element.validationMessage;

        //3. Input to Name and Submit - Verify message password
        sendkeyToElementByJS(nameTextbox, "mie");
        clickToElementByJS(submitButton);
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage(passwordTextbox), "Please fill out this field.");

        //4. Input password and Submit - Verify message email
        sendkeyToElementByJS(passwordTextbox, "123456");
        clickToElementByJS(submitButton);
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage(emailTextbox), "Please fill out this field.");

        //5. Input invalid email: 123!@##$$ and Submit - Verify message email
        sendkeyToElementByJS(emailTextbox, "123!@##$$");
        clickToElementByJS(submitButton);
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage(emailTextbox), "Please enter an email address.");

        //6. Input valid email: sa17821@gmail.com and Submit - Verify message address
        sendkeyToElementByJS(emailTextbox, "sa17821@gmail.com");
        clickToElementByJS(submitButton);
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage(addressTextbox), "Please select an item in the list.");

    }

    @Test
    public void tc04_remove_attribute(){
        //1. Access page
        navigateToUrlByJS("https://demo.guru99.com/v4/");
        sleepInSecond(5);

        //2. Login with user: mngr578942 and pass: gamAgYd
        sendkeyToElementByJS("//input[@name='uid']","mngr578942");
        sendkeyToElementByJS("//input[@name='password']","gamAgYd");
        clickToElementByJS("//input[@name='btnLogin']");
        sleepInSecond(2);

        //3. Select New customer
        clickToElementByJS("//a[text()='New Customer']");
        sleepInSecond(2);

        //4. Input valid data > click on submit
        sendkeyToElementByJS("//input[@name='name']", "Mie");
        clickToElementByJS("//td/input[@value='f']");
        sendkeyToElementByJS("//input[@name='name']", "Quang Binh");
        clickToElementByJS("//input[@name='sub']");
        //Chua biet cach select date of birth

        //5. Verify create new customer successfully

    }
    @Test
    public void tc05_create_account(){
        //1. Access page
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSecond(5);

        //2. CLick on link My account on header
        clickToElementByJS("//header[@id='header']//span[text()='Account']");
        clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
        sleepInSecond(2);

        //3. Click create an account button to navigate to register page
        clickToElementByJS("//a[@title='Create an Account']");
        sleepInSecond(2);

        //4. Input valid data on field: first name, last name, email address, password, confirm password
        sendkeyToElementByJS("//input[@id='firstname']", "Hong");
        sendkeyToElementByJS("//input[@id='middlename']", "lau");
        sendkeyToElementByJS("//input[@id='lastname']", "Mong");
        sendkeyToElementByJS("//input[@id='email_address']", "hong" + getRandomNumber() +"@gmail.net");
        sendkeyToElementByJS("//input[@id='password']", "1457RT#$");
        sendkeyToElementByJS("//input[@id='confirmation']", "1457RT#$");

        //5. Click on register button
        clickToElementByJS("//button[@title='Register']");
        sleepInSecond(2);

        //6. Verify message: Thank you for registering with Main Website Store
        Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));

        //7. Logout
        clickToElementByJS("//header[@id='header']//span[text()='Account']");
        clickToElementByJS("//a[@title='Log Out']");
        sleepInSecond(5);

        //8. navigate to Home page
        Assert.assertEquals(jsExecutor.executeScript("return document.URL"),"http://live.techpanda.org/index.php/");

    }
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    public int getRandomNumber(){
        return new Random().nextInt(9999);
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
