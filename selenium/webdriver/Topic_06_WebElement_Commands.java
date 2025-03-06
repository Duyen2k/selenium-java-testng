package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebElement_Commands {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_() {
            driver.get("https://www.facebook.com/");
    //I-Thao tác lên
        //Tương tác trực tiếp lên
        driver.findElement(By.cssSelector(""))  ;
        //Thao tác nhiều lần lên 1 element => khai báo biến.Nếu ko khai báo sẽ phải nhập lặp lại
        WebElement element= driver.findElement(By.cssSelector(""))  ;
        //Xóa dữ liệu ở trong 1 editable element
            element.clear();//**
        //Nhập dữ liệu vào 1 editable element (nhập)
            element.sendKeys("Duyenne");//**
//        firstNameTextbox.isDisplayed();

        //1-Element cha dùng 1 laoij locator và element con dùng 1 loại locator
        driver.findElement(By.cssSelector("div.form-fields"))
                .findElement(By.xpath("//input[@id='FirstName]"));

        //2-Element cha và con dùng cùng 1 loại locator
        driver.findElement(By.cssSelector("div.form-fields input#FirstName")) ;
        //Tìm một element vs locator là tham số truyền vào
        driver.findElement(By.cssSelector("")); //**
        // Tìm nhiều elements vs locator là tham số truyền vào
        driver.findElements(By.cssSelector("")); //**
        //Click lêm clickable element
        //Button/Checkbox/Radio button/Link/Image/Dropdown
            element.click();//**
        //Tương đương vs submit thông tin gửi lwn server
        //Giả lập hành vu Enter của End User
        //Register/Login/Search/...
        element.submit();

    //II- Verify thông tin/dữ liệu đã action
        //Kiểm tra 1 element có hiển thị hay ko? Hiển thị thì có màu và có kích thước(user có thể nhìn thấy và thao tác)
        //Áp dụng cho tâất cả các loại element
        element.isDisplayed();//**
        //Kiểm tra 1 element đã được chọn hay chưa? Trả về bằng 'true'
        //Áp dụng: Radio/Checkbox/Dropdown
        element.isSelected();//*
        //Kiểm tra 1 element có cho phép thao tác lên hay ko? thường test tính năng phân quyền
        //Cho phép sửa dữ liệu
        //true= cho phép sửa dữ liệu/thao tác
        //false= bị disable
        element.isEnabled();

    //III-Lấy dữ liệu: là những hàm get
         //Lấy ra chiều rộng/chiều cao cửa element
         element.getSize() ;
         //Lấy ra text của 1 element(nếu text nằm ngoài thì dùng getText)
         element.getText();//**
         //Lấy ra text của attribute
         element.getAttribute("placeholder");//*
        //Shadow DOM
         element.getShadowRoot();//*
         //Dev frontend dùng nhiều
         element.getAriaRole();
         element.getDomAttribute("");
         element.getDomProperty("");
         element.getAccessibleName();
         //Lấy màu trường/background/Font Size,...=>CSS
         element.getCssValue("background-color");//*
         element.getCssValue("font-size");
         element.getCssValue("font-family");

         Rectangle elementRect = element.getRect();

         //Lấy ra chiều rộng/chiều cao cuủa element
         element.getSize();
         elementRect.getPoint();
         //Lây ra vị trí của element (góc trên bên trai) so ới lại browser
         element.getLocation();

         //Lấy ra vị trí tên thẻ HTML của element
         //By.id/class/name/css
         element.getTagName();
         driver.findElement(By.id("email")).getTagName();
         //input

         //Take Screenshot(chụp ảnh lỗi)
         element.getScreenshotAs(OutputType.FILE);
         element.getScreenshotAs(OutputType.BYTES);
         element.getScreenshotAs(OutputType.BASE64); //chuyển hình ảnh sang text và ngc lai //*




        }

        @Test
        public void TC_02_() {
            driver.get("https://www.facebook.com/");
        }
    @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }
