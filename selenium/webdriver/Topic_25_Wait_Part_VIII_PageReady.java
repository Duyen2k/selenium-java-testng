package webdriver;

import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_25_Wait_Part_VIII_PageReady {

    WebDriver driver;

    //Gọi là khai báo
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

    }


    @Test
    public void TC_01_Element_Invisible()  {
        driver.get("https://api.orangehrm.com/");
        //Gọi là khởi tạo
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        //Loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

        //Text hiển thị
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#project h1"),"OrangeHRM REST API Documentation"));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),"OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_02_All_Element() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //Nếu như tất cả các page trpng application đều có sự kiện loading thì nên viết thành hàm dùng chung(Reusable Method)
        //Để gọi ra dùng cho tất cả các page
        Assert.assertTrue(isAllLoadingiconInvisible());

    //Click vào PIM
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(isAllLoadingiconInvisible());

    //Verify text PIM (breadcum)
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span[class='oxd-topbar-header-breadcrumb']>h6"),"PIM"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span[class='oxd-topbar-header-breadcrumb']>h6")).getText(),"PIM");

    //Click vào Time
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']")));
        driver.findElement(By.xpath("//span[text()='Time']")).click();
        Assert.assertTrue(isAllLoadingiconInvisible());


    //Verify text oxd-topbar-header-breadcrumb-module
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span[class='oxd-topbar-header-breadcrumb'] h6"),"Time"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span[class='oxd-topbar-header-breadcrumb'] h6")).getText(),"Time");
    }
    public boolean isAllLoadingiconInvisible(){
       return explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));

    }

    @Test
    public void TC_03_(){
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        //chỉ dùng explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.title>strong")));

        driver.findElement(By.cssSelector("input.email")).clear();
        driver.findElement(By.cssSelector("input.email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input.password")).clear();
        driver.findElement(By.cssSelector("input.password")).sendKeys("admin");

        //click vào Login button
        driver.findElement(By.cssSelector("div.buttons>button")).click();

        //Đợi loading icon invisibility
        Boolean isloadingiconInvisible=explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy>span")));
        Assert.assertTrue(isloadingiconInvisible);

        //Click sang trang Customer
        driver.get("https://admin-demo.nopcommerce.com/Admin/Customer/List");
        Assert.assertTrue(isloadingiconInvisible);

        //Click sang trang Orders
        driver.get("https://admin-demo.nopcommerce.com/Admin/Order/List");
        Assert.assertTrue(isloadingiconInvisible);

        //Logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //Đợi title logic page visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.title>strong")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title>strong")).getText(),"Welcome, please sign in!");

    }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
