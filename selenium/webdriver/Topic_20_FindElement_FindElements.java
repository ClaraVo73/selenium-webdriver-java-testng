package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_20_FindElement_FindElements {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void tc01_findElement(){
        //case1.  Tìm thấy duy nhat 1 element/1 node
        // Tim thấy va thao tac truc tiep len node do
        // Vi no tim thay nen khong can phai cho het timeout 15s
        driver.findElement(By.cssSelector("input#email"));

        //case2. Tim thay nhieu hon 1 element/node
        // No se thao tac voi node dau tien va khong quan tam cac node con lai
        // Neu bat locator sai thi se tim sai
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("ppp11234@gmail.com");

        //case3. Khong tim thay element/ node nao
          // 0.5s tim lai 1 lan
          //  den khi het thoi gian 15s ma ko tim ra thi danh fail testcase tai step nay
          // throw ra 1 ngoai le: NoSuchElementException
        // khong run step tiep theo
        driver.findElement(By.cssSelector("input[type='check']"));

    }

    @Test
    public void tc02_findElements(){
        //case2: Tìm thấy duy nhat 1 element/1 node
        // Tim thấy va luu no vao list = 1 element
        // Vi no tim thay nen khong can phai cho het timeout 15s
        List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
        System.out.println("List element number = " + elements.size());

        //case2: Tim thay nhieu hon 1 element/node
        // Tim thay va luu no vao list = element tuong ung
        elements = driver.findElements(By.cssSelector("input"));
        System.out.println("List element number = " + elements.size());

        //case3:  Khong tim thay element/ node nao
        // 0.5s tim lại 1 lan
        // Neu trong thoi gian 15s ma khong tim thay element thi se khong danh fail
        //  List empty
        // chay  step tiep theo

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
