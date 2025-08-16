package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    void successfullyWithValidAccount(){
        // thứ tự ưu tiên id > name > tagName > cssSelector > xpath
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
        /*
        * Một cách so sánh khác:
        * String successMessage = driver.findElement(By.className("success")).getText();
        * Assert.assertTrue(successMessage.contains("You logged into a secure area!"));
         */
        driver.quit();

    }

    @Test
    void failedWithEmptyInput() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        String invalidMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(invalidMessage.contains("Your username is invalid!"));
        driver.quit();
    }

    // Nhập đúng user và sai password
    @Test
    void failedWithInvalidPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("12345@Abc");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        String invalidMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(invalidMessage.contains("Your password is invalid!"));
        driver.quit();
    }
    @Test
    void failedWithInvalidUsername() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("testacb");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        String invalidMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(invalidMessage.contains("Your username is invalid!"));
        driver.quit();
    }

    @Test
    void failedWithInvalidBoth() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("testacb");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!GH");
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        String invalidMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(invalidMessage.contains("Your username is invalid!"));
        driver.quit();
    }
}
