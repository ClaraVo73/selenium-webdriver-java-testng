package listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testNG.Topic_09_Listener;

import java.io.File;

public class ExtentReportListener implements ITestListener {
   @Override
    public void onTestFailure(ITestResult result){

   }
    @Override
    public void onTestFailure(ITestResult result){

    }
    @Override
    public void onTestFailure(ITestResult result){
        TakesScreenshot t = (TakesScreenshot) Topic_09_Listener.driver;
        File srcFile = t.getScreenshotAs(OutputType.FILE);
        try{
            File destFile = 
        }
    }
}
