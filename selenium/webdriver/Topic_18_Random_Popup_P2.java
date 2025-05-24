package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Topic_18_Random_Popup_P2 {

    WebDriver driver;
    Actions action;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

        @Test
        public void TC_01_Not_Found_In_DOM_html() throws InterruptedException {
            driver.get("https://dehieu.vn/");
            Thread.sleep(5000);
            By popup=By.cssSelector("div.modal-content");
            //TH1: Nếu pop-up hiển thị thì close và click vào Đăng Nhập
            if(driver.findElement(popup).isDisplayed()){
                //Close Random Diaglog
                driver.findElement(By.cssSelector("button.close")).click();
                //Verify pop-up ko còn hiển thị
                Assert.assertFalse(driver.findElement(popup).isDisplayed());
                //Click vào Đăng Nhập
                driver.findElement(By.cssSelector("a.signin-site-menu")).click();
            }else
            //TH2: Nếu pop-up ko hiển thị thì click Đăng Nhap luôn
                driver.findElement(By.cssSelector("a.signin-site-menu")).click();
            Thread.sleep(2000);
            //Verify form đăng nhập hiển thị lên màn hình
            Assert.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());


        }

        @Test
        public void TC_02_IN_DOM() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        By popup= By.cssSelector("div.pum-container");
        Thread.sleep(3000);
        //Kiểm tra qua if xem có Dialog xuất hiện ko-nếu có thì đóng
        if(driver.findElement(popup).isDisplayed()){
            driver.findElement(By.cssSelector("button.pum-close")).click();
            Thread.sleep(2000);
        }
        driver.findElement(By.xpath("//a[text()='Giới thiệu']")).click();
        driver.findElement(By.xpath("//a[text()='Về VNK']")).click();
        }

        //Nếu cloe pop-up mà vẫn còn trong DOM(html) thì chỉ cần Assert.assertFalse là được vì nó vẫn ở trong DOM
        //Nếu close pop-up maf nó mất khi ko tìm thấy thì isDissplayed ko tìm được(vì ko có để hiển thị)=> dùng size()=0
    @Test
    public void TC_03_In_DOM() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        Thread.sleep(2000);
        action= new Actions(driver);
        By Dialog=By.cssSelector("div.pop-container");

        //Kiểm tra qua if xem có Dialog xuất hiện ko-nếu có thì đóng
        if (driver.findElement(Dialog).isDisplayed()){
            action.moveToElement(driver.findElement(By.cssSelector("div.close"))).perform();
            action.click(driver.findElement(By.cssSelector("div.close"))).perform();
            Thread.sleep(2000);
            System.out.println("Chạy qua if");
        }
        //Xử lý hover
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Mobile']"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//li[@class='mobile']/a[text()='KMPlayer']"))).perform();
        action.click(driver.findElement(By.xpath("//li[@class='mobile']/a[text()='KMPlayer']"))).perform();
        //Verify Dialog ko còn hiển thị
        Assert.assertEquals(driver.findElements(Dialog).size(),1);

        //Verify màn hình được mở ra
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='l_banner']/following-sibling::div//h1")).getText(),"KMPlayer - Media Player for Android/iOS");

    }

    @Test
    public void TC_04_Not_In_DOM() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(15000);
        By Dialog=By.xpath("//div[@data-title='Newsletter Free eBooks' and not(contains(@style,'display:none'))]");

        if(driver.findElements(Dialog).size()>0 && driver.findElements(Dialog).get(0).isDisplayed()){
            driver.findElement(By.xpath("//a[contains(@onclick,'lepopup_close();')]")).click();
            System.out.println("Có hiển thị pop-up");
        }
            driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
            driver.findElement(By.cssSelector("button#search-submit")).click();
            driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed();

            //Verify pop-up (Not in DOM) ko còn hiển thị nữa
            Assert.assertEquals(driver.findElements(Dialog).size(),0);


    }

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
