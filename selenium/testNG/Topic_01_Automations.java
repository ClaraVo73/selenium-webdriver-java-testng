package testNG;

import org.testng.annotations.*;

public class Topic_01_Automations {
    @Test()
    public void TC_01(){
        System.out.println("Testcase 01");

    }
    @Test()
    public void TC_02(){
        System.out.println("Testcase 02");

    }

    @BeforeMethod()
    public void beforeMethod(){
        System.out.println("Before Method");

    }

    @AfterMethod()
    public void afterMethod(){
        System.out.println("After Method");
    }
    @BeforeClass()
    public void beforeClass(){
        System.out.println("Before class");
    }
    @AfterClass()
    public void afterClass(){
        System.out.println("After class");
    }
    @BeforeTest()
    public void beforeTest(){
        System.out.println("Before Test");
    }
    @AfterTest()
    public void afterTest(){
        System.out.println("After test");
    }
    @BeforeSuite()
    public void beforeSuite(){
        System.out.println("Before suite");
    }
    @AfterSuite()
    public void afterSuite(){
        System.out.println("After suite");
    }

}
