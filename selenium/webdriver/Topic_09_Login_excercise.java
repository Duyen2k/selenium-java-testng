package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Login_excercise {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Empty_email_password() {
            driver.get("http://live.techpanda.org/");
            driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[text()='My Account']")).click();
            driver.findElement(By.cssSelector("button[title='Login']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
            Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");


        }

        @Test
        public void TC_02_invalidEmail() {
            driver.get("http://live.techpanda.org/");
            driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[text()='My Account']")).click();
            driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("1234@1234");
            driver.findElement(By.cssSelector("button[title='Login']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
        }
        @Test
        public void TC_03_Password_lessthan_6 (){
            driver.get("http://live.techpanda.org/");
            driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[text()='My Account']")).click();
            driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("automationtest@gmail.com");
            driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("1234");
            driver.findElement(By.cssSelector("button[title='Login']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
        }
    @Test
    public void TC_04_wrongPassword_or_wrongEmail () {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[text()='My Account']")).click();
        driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("123123123");
        driver.findElement(By.cssSelector("button[title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
    }

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
