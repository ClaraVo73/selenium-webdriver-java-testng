package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void tc01_jquery(){
//1.Go to link
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        //Chon item cho speed dropdown
        //span#speed-button : parentcss khi click vao dropdown
        //ul#speed-menu div[role='option: allItemCss lay all item khi dropdown do xuong. lay dung dong co text
        selectItemInDropdown("span#speed-button","ul#speed-menu div[role='option']","Faster");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");
    }

    @Test
    public void tc02_reactjs(){
//1.Go to link
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        //Chon item cho speed dropdown
        selectItemInDropdown("i.dropdown.icon","span.text","Matt");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");

        selectItemInDropdown("i.dropdown.icon","span.text","Christian");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");

        selectItemInDropdown("i.dropdown.icon","span.text","Jenny Hess");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Jenny Hess");
    }

    @Test
    public void tc03_vuejs(){
//1.Go to link
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        //Chon item cho speed dropdown
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

        //Chon item cho speed dropdown
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

        //Chon item cho speed dropdown
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

    }

    @Test
    public void tc04_editable(){
//1.Go to link
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        //Chon item cho speed dropdown
        enterAndSelectItemInDropdown("input.search","span.text","Angola");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Angola");

        //Chon item cho speed dropdown
        enterAndSelectItemInDropdown("input.search","span.text","Benin");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Benin");

        //Chon item cho speed dropdown
        enterAndSelectItemInDropdown("input.search","span.text","Aland Islands");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aland Islands");
    }
    public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem){
        //1.Click vao mot the bat ky de cho no xo ra het cac item cua dropdown
        driver.findElement(By.cssSelector(parentCss)).click();

        //2.Wait all item are loaded successfully
        //Dua tat ca cac item trong dropdown vao list
       // explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

        //3. Find item need use (dung vong lap duyet qua de tim)
        for (WebElement tempItem : speedDropdownItems){
            //4. Kiem tra cái text cua item dung voi cai minh mong muon
            String itemText = tempItem.getText();
            System.out.println(itemText);
            //5. Check expected text
            if (itemText.trim().equals(expectedTextItem)){
                //trim de xoa khoang trang trong text
                //6. Click on this item
                tempItem.click();
                // Thoat ra khoi vong lap
                break;
            }
        }
    }

    public void enterAndSelectItemInDropdown(String textboxcss, String allItemCss, String expectedTextItem){
        //1. Nhap expected va xo ra cac item matching
        driver.findElement(By.cssSelector(textboxcss)).clear();
        driver.findElement(By.cssSelector(textboxcss)).sendKeys(expectedTextItem);
        sleepInSecond(1);

        //2.Wait all item are loaded successfully
        //Dua tat ca cac item trong dropdown vao list
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

        //3. Find item need use (dung vong lap duyet qua de tim)
        for (WebElement tempItem : speedDropdownItems){
            //4. Kiem tra cái text cua item dung voi cai minh mong muon
            String itemText = tempItem.getText();
            System.out.println(itemText);
            //5. Check expected text
            if (itemText.trim().equals(expectedTextItem)){
                //trim de xoa khoang trang trong text
                //6. Click on this item
                tempItem.click();
                // Thoat ra khoi vong lap
                break;
            }
        }
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
