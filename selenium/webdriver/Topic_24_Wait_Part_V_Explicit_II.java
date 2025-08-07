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

public class Topic_24_Wait_Part_V_Explicit_II {

    WebDriver driver;
    WebDriverWait explicitWait;
    //Dùng Explicit trong TH kết hợp với các hàm khác
    By startbutton = By.cssSelector("div#start>button");
    By loadingicon = By.cssSelector("div#loading");
    By hellotext = By.cssSelector("div#finish>h4");


    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test(description = "Thời gian explicit bằng 0")
    public void TC_01_() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait=new WebDriverWait(driver, Duration.ofSeconds(0));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startbutton));
        driver.findElement(startbutton).click();
    //Có 2 thời điểm để verify element
    //1.Chờ cho step trước hoàn thành (loading icon biến mất/ invisible)-ko quan tâm thằng đằng sau(Hello world)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingicon));
    //2.Chờ step sau xuất hiện (Hello text hiển thị/ visible) - ko quan tâm step trước (Loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hellotext));
        
     }

    @Test(description = "Thời gian explicit ngắn hơn điều kiện xảy ra")
    public void TC_02_() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait=new WebDriverWait(driver, Duration.ofSeconds(3));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startbutton));
        driver.findElement(startbutton).click();
        //Có 2 thời điểm để verify element
        //1.Chờ cho step trước hoàn thành (loading icon biến mất/ invisible)-ko quan tâm thằng đằng sau(Hello world)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingicon));
        //2.Chờ step sau xuất hiện (Hello text hiển thị/ visible) - ko quan tâm step trước (Loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hellotext));
    }

    @Test(description = "Thời gian explicit dài hơn điều kiện xảy ra")
    public void TC_03_() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait=new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startbutton));
        driver.findElement(startbutton).click();
        //Có 2 thời điểm để verify element
        //1.Chờ cho step trước hoàn thành (loading icon biến mất/ invisible)-ko quan tâm thằng đằng sau(Hello world)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingicon));
        //2.Chờ step sau xuất hiện (Hello text hiển thị/ visible) - ko quan tâm step trước (Loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hellotext));
    }

    @Test(description = "Thời gian explicit dài hơn điều kiện xảy ra")
    public void TC_04_() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait=new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startbutton));
        driver.findElement(startbutton).click();
        //Có 2 thời điểm để verify element
        //1.Chờ cho step trước hoàn thành (loading icon biến mất/ invisible)-ko quan tâm thằng đằng sau(Hello world)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingicon));
        //2.Chờ step sau xuất hiện (Hello text hiển thị/ visible) - ko quan tâm step trước (Loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hellotext));
    }


    //Bài tập chọn date picker
    @Test(description = "Set thời gian đợi =0/3s-sử dụng invisibility")
    public void TC_01_datepicker(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(3));

        By textdate=By.cssSelector("td[title='Thursday, July 10, 2025']");
        explicitWait.until(ExpectedConditions.elementToBeClickable(textdate));
        driver.findElement(textdate).click();

        //Đợi/Verify loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body[contains(@class,'demo-page')]/div/div[@class='raDiv']")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer div.RadAjaxPanel span")).getDomProperty("textContent"),"Thursday, July 10, 2025");

    }

    @Test(description = "Set thời gian đơi 0/3s-sử dụng visibility")
    public void TC_02_datepicker(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(3));

        //Chờ cho Calendar hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.contentWrapper")));

        //Hiển thị "No selected dates to display"
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='No Selected Dates to display.']")));

        //Chọn một ngày để click
        By selecteddate12=By.cssSelector("td[title='Saturday, July 12, 2025']");
        explicitWait.until(ExpectedConditions.elementToBeClickable(selecteddate12)).click();

        //Đợi đêến khi Ajax loading icon ko còn nữa
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body[contains(@class,'demo-page')]/div/div[@class='raDiv']")));

        //Đợi đến khi ngày được chọn xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Saturday, July 12, 2025']")));

        //Wait cái ngày được chọn có attribute chuyển sang Class=rcSelected rồi
        explicitWait.until(ExpectedConditions.attributeContains(By.xpath("//a[text()='12']/parent::td"),"class","rcSelected"));

        //Verify ngày được chọn =12
        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer div.RadAjaxPanel span")).getDomProperty("textContent"),"Saturday, July 12, 2025");
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='12']/parent::td")).getDomAttribute("class").contains("rcSelected"));
    }


    @AfterMethod
        public void afterClass(){
//            driver.quit();
        }
    }
