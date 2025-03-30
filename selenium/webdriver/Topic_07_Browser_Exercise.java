package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_Browser_Exercise {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Verify_URL() {
            driver.get("http://live.techpanda.org/");
            driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[text()='My Account']")).click();
            //cách-1
            String loginPageURL= driver.getCurrentUrl();
            Assert.assertEquals(loginPageURL,"http://live.techpanda.org/index.php/customer/account/login/");
            //cách-2
            Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
            driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
            Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        //Viết code ko bị lỗi:bắt đầu ở đâu thì kết thúc ở đó
            //dấu nháy đôi nháy đơn đủ
            //hàm đặt đúng vị trí
        }

        @Test
        public void TC_02_Page_Title() {
            driver.get("http://live.techpanda.org/");
            driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
            Assert.assertEquals(driver.getTitle(),"Customer Login");
            driver.findElement(By.cssSelector("a[title=\'Create an Account\']")).click();
            Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

        }

        @Test
        public void TC_03_Navigation() {
            driver.get("http://live.techpanda.org/");
            driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
            driver.findElement(By.cssSelector("a[title=\'Create an Account\']")).click();
            Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
            driver.navigate().back();
            driver.navigate().forward();
            Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
        }

        @Test
        public void TC_04_Source_Code() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //Tuyệt đối thì dùng AssertEqual
        //Tương đối, là chứa những giá trị bên trong: assertTrue/False
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.cssSelector("a[title=\'Create an Account\']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
        }


    @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }
