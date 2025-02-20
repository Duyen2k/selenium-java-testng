package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_By_Locator {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_ID() {
//Có thể dùng thẻ ID khi ID là duy nhất (search ctrl F nếu chỉ có 1 kết quả trả ra)
// Cú pháp search: Input[id="textfield"]
    driver.get("https://demo.guru99.com/test/login.html");
    driver.findElement(By.id("email")).sendKeys("duyenne@gmail.com");
    }
    @Test
    public void TC_02_Class() {
//Có thể dùng thẻ Class khi Class là duy nhất (search ctrl F nếu chỉ có 1 kết quả trả ra). Tuy nhiên Class ít sử dụng
// Cú pháp search: Input[class="hidden"]
     driver.get("https://demo.guru99.com/test/login.html");
     driver.findElement(By.className("hidden"));
    }
    @Test
    public void TC_03_Name() {
//Có thể dùng thẻ Name khi Name là duy nhất (search ctrl F nếu chỉ có 1 kết quả trả ra). Tuy nhiên Class ít sử dụng
// Cú pháp search: Input[id="textfield"]
    driver.get("https://demo.guru99.com/test/login.html");
    driver.findElement(By.name("back"));
    }

    @Test
    public void TC_04_Link() {
//Chỉ dùng được với đường link có text VD link: ABOUT US
// Chú ý phải ghi đúng toàn bộ text(tuyệt đối) link hiển thị trên UI
    driver.get("http://guru99.com/");
    driver.findElement(By.linkText("Privacy Policy"));

    }

    @Test
    public void TC_05_Partial_Link() {
//Chỉ dùng được với đường link có text VD link: ABOUT US
// Chú ý phải ghi đúng toàn bộ text(tương đối) link hiển thị trên UI
    driver.get("http://guru99.com/");
    driver.findElement(By.partialLinkText("Privacy"));
    }
    @Test
    public void TC_06_Tagname() {
//Sử dụng khi tìm kiếm nhiều Elements giống nhau VD: Tìm tất cả đường link <a> trên cùng 1 trang
    driver.get("https://demo.guru99.com/test/login.html");
    int linkNumber =driver.findElements(By.tagName("a")).size();
    System.out.println("Số lượng link ="+ linkNumber);
    }
    @Test
    public void TC_07_Css() {
//Css vs ID
    driver.get("https://demo.guru99.com/test/login.html");
    int linkNumber=driver.findElements(By.cssSelector("input[id='email']")).size();
    driver.findElements(By.cssSelector("input#email"));
    driver.findElements(By.cssSelector("input#email"));
    System.out.println("Số lượng link="+linkNumber);
    //Css cú pháp: tagname(attribute='value')
    //Xpath cú pháp: //tagname(attribute=value')
    // VD: input[id='search']  ; #search   ;"input#search"

//Css vs Class
        driver.get("https://demo.guru99.com/test/login.html");
        driver.findElement(By.cssSelector("p[class='submit']"));
        driver.findElement(By.cssSelector("p.submit"));
        driver.findElement(By.cssSelector(".submit"));

        driver.get("https://demo.guru99.com/test/login.html");
        driver.findElement(By.cssSelector("div[class='form_content clearfix']"));
        driver.findElement(By.cssSelector("div.form_content.clearfix"));
        driver.findElement(By.cssSelector(".form_content.clearfix"));
    //Ngoại lệ: nếu class có nhiều giá trị(cách nhau bởi khoảng trắng) thì khi viết tắt (dòng 2-VD2) sẽ chỉ viết 1 phần thôi ko viết full
    //Class viết tắt cách nhau bởi dấu chấm(.): 3 cách như trên
//Css vs Name
        //Cứ có name là dùng được hết: chỉ dùng đc 1 cách thôi ko viết tắt đc
        driver.get("https://demo.guru99.com/test/login.html");
        driver.findElement(By.cssSelector("input[name='email']"));


//Css vs Link: lấy toàn bộ link
        driver.get("https://www.guru99.com/");
        driver.findElement(By.cssSelector("a[href='/privacy-policy']"));

//Css vs Partialink: lấy ở đầu thì ^= ;lấy ở giữa thì *= ;lấy ở cuối thì $= sao cho nó là duy nhất mới pass đc
        driver.get("https://www.guru99.com/");
        driver.findElement(By.cssSelector("a[href^='/privacy-p']"));
        driver.findElement(By.cssSelector("a[href*='rivacy-p']"));
        driver.findElement(By.cssSelector("a[href$='ivacy-policy']"));

//Css vs Tagname
        driver.get("https://www.guru99.com/");
        int linkNumber2 =driver.findElements(By.cssSelector("a")).size();
        System.out.println("Số lượng link ="+ linkNumber2);
    }
    @Test
    public void TC_08_Xpath() {
//Mạnh nhưng chạy chậm, ko có viết tắt, css chuẩn thêm // và @
driver.get("https://demo.guru99.com/test/login.html");
//Xpatch vs ID
driver.findElement(By.xpath("//input[@id='email']"));
//Xpath vs Class
driver.findElement(By.xpath("//div[@class='form_content clearfix']"));
//Xpath vs Name
driver.findElement(By.xpath("//input[@name='passwd']"));

//Xpath vs Link: có theer dùng link trong thẻ <a> hoặc dùng link text VD: About Us (nhưng phải dùng cái text dưới inspect)
driver.get("https://www.guru99.com/");
driver.findElement(By.xpath("//a[text()='Campaigner']"));
driver.findElement(By.xpath("//a[@href=\"https://guru99.live/oz2hbz\"]"));
//Xpath vs partiallink; dùng với cú pháp hàm starts-with
        //tìm ở đầu dùng starts-with, giữ dùng contains, cuối dùng ends-with
driver.findElement(By.xpath("//a[starts-with(@href,\"https://guru99.live/rlhfq3\")]"));
driver.findElement(By.xpath("//a[contains(@href,'live/rlhfq\')]"));
//Css có ends-with($=) nhưng Xpath ko có
//driver.findElement(By.xpath("//a[(ends-with(@href,'live/rlhfq3\')]"));

//Tagname
driver.get("https://demo.guru99.com/test/login.html");
int linkNumber =driver.findElements(By.xpath("a")).size();
System.out.println("Số lượng link ="+ linkNumber);

    }
//    @AfterClass
//        public void afterClass(){driver.quit();
//        }

    //Excercise
@Test
    public void TC_Exercise_01_ID() {
    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    driver.findElement(By.id("txtSearch"));
    driver.findElement(By.className("marleft0"));
    driver.findElement(By.name("txtEmail"));
    driver.findElement(By.linkText("chính sách"));
    driver.findElement(By.partialLinkText("sách"));
    //css
    driver.findElement(By.cssSelector("input[id='txtSearch']"));
    driver.findElement(By.cssSelector("input#txtSearch"));
    driver.findElement(By.cssSelector("input[class='marleft0']"));
    driver.findElement(By.cssSelector("input.marleft0"));
    driver.findElement(By.cssSelector("input[name='txtEmail']"));
    driver.findElement(By.cssSelector("a[href=\'https://alada.vn/chinh-sach-bao-mat.html\']"));
    driver.findElement(By.cssSelector("a[href^=\'https://alada.vn/chinh-sach-bao']"));
    driver.findElement(By.cssSelector("a[href*='//alada.vn/chinh-sach-bao-mat']"));
    driver.findElement(By.cssSelector("a[href$='vn/chinh-sach-bao-mat.html\']"));

    //xpath
    driver.findElement(By.xpath("//input[@id='txtCPassword']"));
    driver.findElement(By.xpath("//input[@class='marleft0']"));
    driver.findElement(By.xpath("//input[@name='txtCPassword']"));
    driver.findElement(By.xpath("//a[starts-with(@href,'https://alada.vn/dieu-khoan-dich-vu.html')]"));
    driver.findElement(By.xpath("//a[contains(@href,'https://alada.vn/dieu-khoan-dich-vu.html')]"));



}

}




