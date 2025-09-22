package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoLoginTest {
    @Test
    void loginWithValidAccount(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        driver.quit();
    }
    @Test
    void loginWithInvalidPassword(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce001");
        driver.findElement(By.id("login-button")).click();
        String invalidMessage = driver.findElement(By.className("error")).getText();
        Assert.assertTrue(invalidMessage.contains("Epic sadface: Username and password do not match any user in this service"));
        driver.quit();
    }
}
