package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_24_Wait_Part_VI_Mixing {

    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_Only_Implicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().window().maximize();

        //Dùng timeout của Implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("input#email"));

    }

    @Test
    public void TC_02_Only_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Dùng timeout của Explicit(Dùng cái nào thì lỗi của thằng đấy)
        explicitWait= new WebDriverWait(driver,Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-pass")));

        //0s của Implicit
        driver.findElement(By.cssSelector("div#advice-required-entry-pass"));

    }

    @Test
    public void TC_03_Mix_Implicit_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Set implicut/explicit với time = nhau
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start ="+getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-pass")));
        } catch (Exception e) {
            System.out.println("End ="+getDateTimeNow());
            throw new RuntimeException(e);
        }
    }
        public String getDateTimeNow() {
            Date date = new Date();
            return date.toString();
        }

    @Test
    public void TC_04_Mix_Implicit_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Set implicit > explicit
        //Làn 1: 12-10 => chạy hết 12s
        //lần 2: 12-5 => chạy hết 12s
        //Lần 3: 14-5 => chạy hết 14s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("Start2 ="+getDateTimeNow2());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-pass")));
        } catch (Exception e) {
            System.out.println("End2 ="+getDateTimeNow2());
            throw new RuntimeException(e);
        }
    }
    public String getDateTimeNow2() {
        Date date = new Date();
        return date.toString();
    }

    @Test
    public void TC_05_Mix_Implicit_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Set implicit < explicit 
        //Lần 1: 10-12 => chạy 20s
        //Lần 2: 5-12 => chạy 16s
        //Lần 3: 5-10 => chạy 11s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start3 ="+getDateTimeNow3());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-pass")));
        } catch (Exception e) {
            System.out.println("End3 ="+getDateTimeNow3());
            throw new RuntimeException(e);
        }
    }

    //Implicit luôn ưu tiên chạy trước
    //Để tránh hiểu nhầm: Nên set time của 2 wait này bằng nhau

    @Test
    public void TC_03_Only_Explicit_By(){
//        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Dùng timeout của explicit
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));

        //Lấy 10s của Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#emailhii")));
    }

    @Test
    public void TC_04_Only_Explicit_WebElement(){
//        driver.get("https://live.techpanda.org/index.php/customer/account/login/"); (tạo ngaofi beforeMethod/beforeClass để ko bị tính time getURL)

        //Dùng timeout của Explicit
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));

        //Của Explicit nhưng Fail 0s vì find element bị ảnh hưởng bới Implicit(explicit chưa kịp chạy)
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#emailhii"))));
    }

    public String getDateTimeNow3() {
        Date date = new Date();
        return date.toString();
    }

    @AfterMethod
        public void afterClass(){
//            driver.quit();
        }
    }
