package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Register {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Empty_data() {
        //action 1
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action 2
            driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Assert
            Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
            Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
            Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
            Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
            Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
            Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
        }


        @Test
        public void TC_02_Invalid_Email() {
            //action 1
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");
            //action 2
            driver.findElement(By.id("txtFirstname")).sendKeys("Duyenne");
            driver.findElement(By.id("txtEmail")).sendKeys("123...123");
            driver.findElement(By.id("txtCEmail")).sendKeys("123...123");
            driver.findElement(By.id("txtPassword")).sendKeys("123456");
            driver.findElement(By.id("txtCPassword")).sendKeys("123456");
            driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
            driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
            //Assert
            Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
            Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại email hợp lệ");
        }
            @Test
            public void TC_03_Incorrect_ConfirmEmail() {
                //action 1
                driver.get("https://alada.vn/tai-khoan/dang-ky.html");
                //action 2
                driver.findElement(By.id("txtFirstname")).sendKeys("Duyenne");
                driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
                driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail.com");
                driver.findElement(By.id("txtPassword")).sendKeys("123456");
                driver.findElement(By.id("txtCPassword")).sendKeys("123456");
                driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
                driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
                //Assert
                Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
        }

    @Test
    public void TC_04_Invalid_Password() {
        //action 1
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action 2
        driver.findElement(By.id("txtFirstname")).sendKeys("Duyenne");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Incorrect_ConfirmPwd() {
        //action 1
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action 2
        driver.findElement(By.id("txtFirstname")).sendKeys("Duyenne");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Phonenumber_less10() {
        //action 1
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action 2
        driver.findElement(By.id("txtFirstname")).sendKeys("Duyenne");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("098765");
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("098765432101");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("01");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }



    @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }
