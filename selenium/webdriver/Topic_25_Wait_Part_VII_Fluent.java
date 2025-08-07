package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;
//import java.util.NoSuchElementException;
import java.util.function.Function;

public class Topic_25_Wait_Part_VII_Fluent {

    WebDriver driver;

    //Gọi là khai báo
    WebDriverWait explicitWait;
    FluentWait fluentWait;
    FluentWait<WebElement> fluentWaitElement;
    FluentWait<WebDriver> fluentWaitDriver;
    FluentWait<Boolean> fluentWaitBolean;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Gọi là khởi tạo
        //fluentWaitDriver=new FluentWait<>(driver);
        //fluentWaitElement=new FluentWait<>(driver.findElement(By.cssSelector("")));
        //fluentWaitBolean=new FluentWait<>(true);

//        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
    }


    @Test
    public void TC_01_() throws InterruptedException {
    }

    //Tìm element với polling Time là 1s kiểm tra 1 lần (tìm element->  trả về WebElement)
    public WebElement findElement(By by){
    //Khai báo + khởi tạo
        FluentWait fluentWait=new FluentWait(driver);
    //Config Time/Polling/Exception
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

    //Điều kiện
        return (WebElement) fluentWait.until(new Function<WebDriver,WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return  driver.findElement(by);
            }
        });

    }

    //Kiểm tra Element hiển thị: isDisplayed()
    public boolean isElementDisplayed(By by){
        FluentWait fluentWait=new FluentWait<>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        //Điều kiện (dùng until(new Funtion)
        return (boolean) fluentWait.until(new Function<WebElement,Boolean>() {
            @Override
            public Boolean apply(WebElement driver) {
                return driver.findElement(by).isDisplayed() ;
            }
        });
    }

    @Test
    public void TC_02_(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        fluentWaitDriver=new FluentWait<>(driver);

        //Trong vòng 5s cứ mỗi 1/3s sẽ tìm chữ Helloword
        fluentWaitDriver.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(333))
                .ignoring(NoSuchElementException.class);

        String helloText=fluentWaitDriver.until(new Function<WebDriver, String>() {

                            @Override
                            public String apply(WebDriver webDriver) {
                                System.out.println("---finding&return---");
                                return webDriver.findElement(By.cssSelector("div#finish>h4")).getText();
                            }
                        });

        Assert.assertEquals(helloText,"Hello World!");

    }

    @Test
    public void TC_03_(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        fluentWait=new FluentWait<>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class); //Import Exception của Java là sai-> bắn lỗi ngay lần search đầu tiên

        Boolean Countdowntime00= (Boolean) fluentWait.until(new Function<WebDriver,Boolean >() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText().equals("01:01:00");
            }
        });
        Assert.assertTrue(Countdowntime00);


    }

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
