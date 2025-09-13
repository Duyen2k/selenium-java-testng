package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_24_Wait_Part_II_FindElement {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_FindElement() throws InterruptedException {
        //1**Tìm thấy 1 Element
        //Vào sẽ tìm thấy Element ngay chứ ko cần cờ hết Timeout của Implicit
//        driver.findElement(By.cssSelector("input#email"));

        //2**Tìm thấy 0 Element
        //Vào sẽ ko tìm thấy Element và sẽ tìmdđi tìm lại mỗi 0.5s 1 lần cho đến khi hết timeout là 15s=30 lần
        //Đánh Fail testcase tại vị trí này và show lỗi NoSuchElementException

        //Trong trường hợp mà Element ko tìm thấy và vẫn đang tìm lại. Nếu Element có xuất hieenj thì nó vẫn pass
        driver.findElement(By.cssSelector("input#email8888"));

        //3**Tìm thấy nhiều hơn 1 Element
        //Nó sẽ luôn thao tác với thằng đầu tiên(kể ca nó bị ẩn -> sẽ bị lỗi ko thể tương tác lên Element)
        //Do vậy luôn luôn phải tìm thấy duy nhất 1 Element để tương tác
        driver.findElement(By.cssSelector("input:not([type='hidden'])")).sendKeys("TextonSearchbox");

        }

    @Test
    public void TC_02_FindElements(){
        //1**Tìm thấy 1 Element
        //Trả về 1 element + ko cần chờ hết timeout

//        List<WebElement> elementList=driver.findElements(By.cssSelector("input#email"));
//        System.out.println(elementList.size());

        //2**Tìm thấy 0 Element
        //Vào sẽ ko tìm thấy Element và sẽ tìm đi tìm lại mỗi 0.5s 1 lần cho đến khi hết timeout là 15s=30 lần
        //Ko đánh Fail testcase mà trả về list rỗng => 0 element
        List<WebElement> elementList= driver.findElements(By.cssSelector("input#email8888"));
        System.out.println(elementList.size());
        //Ko hiển thị trong html=> Nếu muốn tìm element non-present thì dùng findElement số nhiều
//        Assert.assertEquals(elementList.size(),0);

        //3**Tìm thấy nhiều hơn 1 Element
        //Lấy hết Element để lưu vào List
        List <WebElement> elementList2=driver.findElements(By.cssSelector("input:not([type='hidden'])"));

        //Thao tác lên các element trong list
        elementList2.get(1).sendKeys("Selenium");




    }


    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
