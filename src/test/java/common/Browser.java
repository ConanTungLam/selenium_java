package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

// all static methods
public class Browser {
    private static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    public static void launch(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "safari" -> {
                driver = new SafariDriver();
            }
            case "edge" -> {
                driver = new EdgeDriver();
            }
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void fill(By locator, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
    }

    public static void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    public static String getValue(By locator){
        return find(locator).getAttribute("value");
    }

    public static void captureScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("target/screenshot-%s-%s.png", fileName, System.currentTimeMillis()));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public static List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    public static void check(By locator) {
        if (!isSelected(locator)) click(locator);
    }

    public static void uncheck(By locator) {
        if (isSelected(locator)) click(locator);
    }

    public static boolean isSelected(By locator) {
        return find(locator).isSelected();
    }

    public static boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    public static boolean isEnabled(By locator) {
        return find(locator).isEnabled();
    }
    public static void dragAndDrop(By source, By target){
        actions
                .dragAndDrop(find(source),find(target))
                .perform();
    }

}