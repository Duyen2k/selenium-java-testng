package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_Part_IV_Static {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");

    }
//Implicit ảnh hưởng đến FindElement
//Dùng BeforeMethod thì sẽ chạy cả before/affter cho từng testcase
    //Dùng Before/AfterClass thì nó chỉ áp dụng cho 1 testcase đầu/TC được Run
    @Test(description = "Thời gian implicit bằng 0")
    public void TC_01_() throws InterruptedException {

    //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(0);
    //Step 2: Loading icon(khoảng 5s)

    //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        }

    @Test(description = "Thời gian implicit ngắn hơn thời gian element xuất hiện")
    public void TC_02_() throws InterruptedException {
        //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(3);
        //Step 2: Loading icon(khoảng 5s)

        //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test(description = "Thời gian implicit bằng thời gian element xuất hiện")
    public void TC_03_() throws InterruptedException {
        //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(5);
        //Step 2: Loading icon(khoảng 5s)

        //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test(description = "Thời gian implicit lớn hơn thời gian element xuất hiện")
    public void TC_04_() throws InterruptedException {
        //Step 1: Click vào button START
        driver.findElement(By.cssSelector("div#start>button")).click();
        sleepInSecond(10);
        //Step 2: Loading icon(khoảng 5s)

        //Step 3: Text 'Hello World' xuất hiện
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    //Phải đợi hết 10s mới log ra lỗi
    }

    public void sleepInSecond(long timeInSecond){
            try {
                Thread.sleep(timeInSecond*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    @AfterMethod
        public void afterClass(){
//            driver.quit();
        }
    }
