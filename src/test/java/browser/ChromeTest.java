package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v138.emulation.Emulation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChromeTest {
    @Test
    public void normalModeTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(), "Selenium");

        driver.quit();
    }

    @Test
    public void headlessModeTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }

    @Test
    void openBrowserWithMobileViewMode(){
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 344);
        deviceMetrics.put("height", 882);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }

//    @Test
//    void openBrowserWithOldVersion(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setBrowserVersion("129");
//
//        WebDriver driver = new ChromeDriver(chromeOptions);
//        driver.get("https://www.selenium.dev/");
//        Assert.assertEquals(driver.getTitle(),"Selenium");
//        driver.quit();
//    }
//    @Test
//    void openBrowserWithBetaVersion(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setBrowserVersion("132");
//
//        WebDriver driver = new ChromeDriver(chromeOptions);
//        driver.get("https://www.selenium.dev/");
//        Assert.assertEquals(driver.getTitle(),"Selenium");
//        driver.quit();
//    }
@Test
void openBrowserWithFakeGeoLocation(){
    WebDriver driver = new ChromeDriver();
    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();
    // Mountain view
    devTools.send(Emulation.setGeolocationOverride(
            Optional.of(37.386052),
            Optional.of(-122.083851),
            Optional.of(1),
            Optional.of(100),
            Optional.of(1),
            Optional.of(1),
            Optional.of(0)
    ));
    driver.get("https://the-internet.herokuapp.com/geolocation");
    driver.findElement(By.xpath("//button[.='Where am I?']")).click();
    Assert.assertEquals(driver.findElement(By.cssSelector("#lat-value")).getText(),"37.386052");
    Assert.assertEquals(driver.findElement(By.cssSelector("#long-value")).getText(),"-122.083851");

    driver.quit();
    }
}

