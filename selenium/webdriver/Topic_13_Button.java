package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_13_Button {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Button_Colorcheck() throws InterruptedException {
            driver.get("https://www.fahasa.com/customer/account/create");
            driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
            Thread.sleep(2000);
            //Verify Login button is disable
            Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
            //Verify Login button has grey background
            org.testng.Assert.assertEquals(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color"),"rgba(0, 0, 0, 0)");
            org.testng.Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(),"#000000");
            Thread.sleep(2000);

            //Verify Login button is enable
            driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987204868");
            driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678");
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
            //Verify Login button has red background
            org.testng.Assert.assertEquals(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color"),"rgb(201, 33, 39)");
            org.testng.Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

            //Tuyệt đối ko phân biệt hoa thường
            //text.equalsIgnoreCase()

            //Tương đối:
            //text.contains()
            //text.startsWith()

            //Độ dài text: text.length()
        }

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
