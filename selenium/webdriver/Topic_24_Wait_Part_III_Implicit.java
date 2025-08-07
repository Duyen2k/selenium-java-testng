package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Topic_24_Wait_Part_III_Implicit {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
//Implicit chỉ ảnh hưởng đến FindElement
//Dùng BeforeMethod thì sẽ chạy cả before/affter cho từng testcase
    //Dùng Before/AfterClass thì nó chỉ áp dụng cho 1 testcase đầu/TC được Run
    @Test(description = "Thời gian implicit bằng 0")
    public void TC_01_() throws InterruptedException {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
    //Step 2: Loading icon(khoảng 5s)

    //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

    //Fail tại step 3, do đoạn step2 phải đợi 5s mới load ra được success message nên nó sẽ ko tìm thấy được 'Hello world'.
    //Chạy mất khoảng 0.25s

        }

    @Test(description = "Thời gian implicit ngắn hơn thời gian element xuất hiện")
    public void TC_02_(){
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
        //Step 2: Loading icon(khoảng 5s)

        //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

        //Fail ở step 3, do step 2 loading icon câần 5s, mà mình set c 3s, do vậy ko load được 'Hello World'
        //Chạy mất khoảng 3s
    }

    @Test(description = "Thời gian implicit bằng thời gian element xuất hiện=5s")
    public void TC_03_(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
        //Step 2: Loading icon(khoảng 5s)

        //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

        //Passed, do step 2 loading icon câần 5s, mà mình set 5s, do vậy load được 'Hello World'
        //Chạy mất khoảng 5s
    }
//Tips: Ko nên set timeout nhỏ quá hay lớn qua
    //Vì set timeout nhỏ quá thì có thể ko đủ time để tìm
    //Set timeout lớn quá thì có thể ko tìm thấy sẽ mất nhiều thời gian

    @AfterMethod
        public void afterClass(){
//            driver.quit();
        }
    }
