package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_MultiBrowser {
    WebDriver driver;
    By emailTextbox = By.cssSelector("input#email");
    By passwordTextbox = By.cssSelector("input#pass");
    By loginButton = By.cssSelector("button#send2");

    @Parameters({"browser"})
    @BeforeClass
    public void initialBrowser(String browserName) {
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver=new EdgeDriver();
                break;
            case "CHROME":
                driver=new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not Suport");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(dataProvider = "loginData")
    public void shouldBeLoginToSystem(String username, String password, String zipCode) {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));

        // Mua Hàng

        // Thanh Toán

        // Ship Hàng

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @DataProvider(name = "loginData")
    public Object[][] UserAndPasswordData() {
        //Màng 2 chiều, ánh xạ data lên TC bên trên. Dù có 1TC nhưng sẽ chạy với 3 data
        return new Object[][]{
                {"selenium_11_01@gmail.com", "111111", "65000"}
        };
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
