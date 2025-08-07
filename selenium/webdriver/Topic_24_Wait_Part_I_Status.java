package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_Part_I_Status {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Visible() throws InterruptedException {
         driver.get("https://tiki.vn/");
         Thread.sleep(5000);  //Để chờ close diag-log
        //Điều kiện 1: Element hiển thị trên UI và html
        //Nếu thỏa mãn điều kiện 1 thì gọi là Visible/Displayed -> có trên UI
        //Có trên UI thì 100% có trong html

          driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
          explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='tel']")));
        }

    @Test
    public void TC_02_Invisible_html(){
            driver.get("https://live.techpanda.org/");

            //Điều kiên 2: Element ko hiển thị trên UI nhưng vẫn có trong html
            //Đợi element bị invisible rồi hiển thị(có trong DOM nhưng ko show trên màn hình-vì nó trong dropdown)
            explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
        }

    @Test
    public void TC_03_Invisible_notHTML() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000); //Chỗ này đợi để close Diag-log

        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
        driver.findElement(By.cssSelector("p.login-with-email")).click();

        //Điều kiên 3: Element ko có trên UI và trong html (thường dùng test chức năng phân quyền)
        //Vì sao lại lâu -> tìm element, tìm đi tìm lại trong vòng 10s mới trả KQ, sau đó mới check invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='tel']")));
    }

    @Test
    public void TC_03_Present(){
        driver.get("https://live.techpanda.org/");
        //Present: chỉ cần có trong html - ko quan tâm trên giao diện UI có ko

        //Điều kiêm 1: Element có trên UI và cây HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.footer-container [title='My Account']")));

        //Điều kiên 2: Element ko hiển thị trên UI nhưng vẫn có trong html (cái này trong dropdown)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div#header-account a[title='My Account']")));

    }

    @Test
    public void TC_02_Staleness() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000); //Chỗ này đợi để close Diag-log

        //Điều kiên 3: Element ko có trên UI và trong html (thường dùng test chức năng phân quyền)
        //Điều kiện cần: Invisible not in ODM
        //Điều kiện đủ: Tại thời điểm A, nó có xuất hiện trong HTML(present) và sau đó dùng element kiểm tra lại tại thời điểm B thì ko còn trong HTML

        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
        //Phonetextbox có xuất hiện
        WebElement PhoneTextbox =driver.findElement(By.cssSelector("input[name='tel']"));

        driver.findElement(By.cssSelector("p.login-with-email")).click();

        //Chạy vế từ trong ra ngoài nên ko dùng trưc tiếp By element vì khi này element ko còn trong html nữa
        explicitWait.until(ExpectedConditions.stalenessOf(PhoneTextbox));
        //Do vậy ko truyền trưcj tiếp element vào đây, vì nó sẽ trở thành vế riêng, khi chạy nó ko tìm thấy sẽ Fail
    }

    @Test
    public void TC_04_AutomationFC(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

    }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
