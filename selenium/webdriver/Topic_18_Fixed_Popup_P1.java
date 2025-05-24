package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_Fixed_Popup_P1 {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Not_Found_In_DOM_html() throws InterruptedException {
            driver.get("https://ngoaingu24h.vn/");
            driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

            By Diaglog=By.cssSelector("div.MuiDialog-container div[role='dialog']");

            //Kiểm tra Diaglog xuât hiện:
            Assert.assertTrue(driver.findElement(Diaglog).isDisplayed());
            //Điền thông tin đăng nhập
            driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("Duyenne");
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234");
            driver.findElement(By.xpath("//div[@id='custom-dialog']//button[text()='Đăng nhập']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Bạn đã nhập sai tài khoản hoặc mật khẩu!']")).isDisplayed());
            driver.findElement(By.cssSelector("button.close-btn")).click();
            Thread.sleep(2000);
            //Kiểm tra pop-up biến mất dùng findElements số nhiều
            Assert.assertEquals(driver.findElements(Diaglog).size(),0);

        }

        @Test
        public void TC_02_KynaEnglish() throws InterruptedException {
            driver.get("https://skills.kynaenglish.vn/dang-nhap");
            By PopupDiaglog=By.cssSelector("div.modal-content div.right");
            //Kiểm tra hiển thị pop-up
            Assert.assertTrue(driver.findElement(PopupDiaglog).isDisplayed());
            //Nhập thông tin đăng nhập
            driver.findElement(By.cssSelector("input#user-login")).sendKeys("Automationtest@gmail.com");
            driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
            driver.findElement(By.cssSelector("button#btn-submit-login")).click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
        }

        //Nếu cloe pop-up mà vẫn còn trong DOM(html) thì chỉ cần Assert.assertFalse là được vì nó vẫn ở trong DOM
        //Nếu close pop-up maf nó mất khi ko tìm thấy thì isDissplayed ko tìm được(vì ko có để hiển thị)=> dùng size()=0
    @Test
    public void TC_03_Not_In_DOM() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
        By popupLogin = By.xpath("//div[@role='dialog']/div");
        //Kiểm tra hiển thị pop-up Diaglog
        Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());
        //Bỏ trống ko nhập sdt và bấm Tiếp Tục
        driver.findElement(By.xpath("//button[text()='Tiếp Tục']")).click();
        Thread.sleep(2000);
        //Verify error text
        Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess")).getText(),"Số điện thoại không được để trống");
        //Close pop-up
        driver.findElement(By.cssSelector("button.btn-close")).click();
        Thread.sleep(2000);
        //Kiểm tra không còn hiển thị pop-up Diaglog
        Assert.assertEquals(driver.findElements(popupLogin).size(),0);


    }

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
