package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static common.Browser.*;

public class BaseTest {
    @BeforeClass
    protected void setup(){
        launch("chrome");
    }

    @AfterMethod
    protected void captureScreenshotWhenTestFail(ITestResult testResult){
        if(!testResult.isSuccess()){
            captureScreenshot(testResult.getMethod().getMethodName());
        }
    }

    @AfterClass
    protected void teardown(){
        quit();
    }
}