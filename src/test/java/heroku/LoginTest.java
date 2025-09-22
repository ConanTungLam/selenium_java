package heroku;

import base.BaseTest;
import heroku.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static common.Browser.*;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    @BeforeMethod
    void openPage(){
        loginPage = new LoginPage();
        loginPage.open();
    }

    @DataProvider
    Object[][] testData(){
        return new Object[][]{
                {"tomsmit","SuperSecretPassword!","https://the-internet.herokuapp.com/login","error","Your username is invalid!"},
                {"tomsmith","SuperSecretPassword","https://the-internet.herokuapp.com/login","error","Your password is invalid!"},
        };
    }

    @Test(dataProvider = "testData")
    void withCredential(String username, String password,String expectedUrl, String expectedMessageType,String expectedMessageContent)  {
        loginPage.login(username,password);
        Assert.assertEquals(getCurrentUrl(),expectedUrl);

        String successMessage = loginPage.getFlashMessage(expectedMessageType);
        Assert.assertTrue(successMessage.contains(expectedMessageContent));
    }

//    WebDriver driver;

//    @BeforeClass
//    void initBrowser(){
//        driver = new ChromeDriver();
//    }

//    @BeforeMethod
//    void reloadLoginPage() throws InterruptedException {
//        Thread.sleep(2000);
//        driver = new ChromeDriver();
//        driver.get("https://the-internet.herokuapp.com/login");
//        Thread.sleep(2000);
//    }
//
//    @Test
//    void successfullyWithValidAccount(){
//        // thứ tự ưu tiên id > name > tagName > cssSelector > xpath
//        driver.findElement(By.id("username")).sendKeys("tomsmith");
//        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
//        driver.findElement(By.cssSelector("button[type=submit]")).click();
//        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
//        /*
//        * Một cách so sánh khác:
//        * String successMessage = driver.findElement(By.className("success")).getText();
//        * Assert.assertTrue(successMessage.contains("You logged into a secure area!"));
//         */
//
//    }
//
//    @Test
//    void failedWithEmptyInput() {
//        driver.findElement(By.id("username")).sendKeys("");
//        driver.findElement(By.id("password")).sendKeys("");
//        driver.findElement(By.cssSelector("button[type=submit]")).click();
//        String invalidMessage = driver.findElement(By.className("error")).getText();
//        Assert.assertTrue(invalidMessage.contains("Your username is invalid!"));
//    }
//
//    // Nhập đúng user và sai password
//    @Test
//    void failedWithInvalidPassword() {
//        driver.findElement(By.id("username")).sendKeys("tomsmith");
//        driver.findElement(By.id("password")).sendKeys("12345@Abc");
//        driver.findElement(By.cssSelector("button[type=submit]")).click();
//        String invalidMessage = driver.findElement(By.className("error")).getText();
//        Assert.assertTrue(invalidMessage.contains("Your password is invalid!"));
//    }
//    @Test
//    void failedWithInvalidUsername() {
//        driver.findElement(By.id("username")).sendKeys("testacb");
//        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
//        driver.findElement(By.cssSelector("button[type=submit]")).click();
//        String invalidMessage = driver.findElement(By.className("error")).getText();
//        Assert.assertTrue(invalidMessage.contains("Your username is invalid!"));
//    }
//
//    @Test
//    void failedWithInvalidBoth() {
//        driver.findElement(By.id("username")).sendKeys("testacb");
//        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!GH");
//        driver.findElement(By.cssSelector("button[type=submit]")).click();
//        String invalidMessage = driver.findElement(By.className("error")).getText();
//        Assert.assertTrue(invalidMessage.contains("Your username is invalid!"));
//    }
//
//    @AfterMethod
//    void quitBrowser(){
//        driver.quit();
//    }

}
