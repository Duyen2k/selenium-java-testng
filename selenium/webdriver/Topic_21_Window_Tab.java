package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.Set;

public class Topic_21_Window_Tab {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Github() throws InterruptedException {
            driver.get("https://automationfc.github.io/basic-form/index.html");
//Trường hợp có 2 tab
            String githubID=driver.getWindowHandle();
        //Lấy ra windowID của một cửa sổ đang active(driver đang đứng đó)
            driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        //Lấy ra Window ID của 2 cửa sổ/tab: dùng Set<tring> là duy nhất
            switchToWindowByID(githubID);
            driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Automation Test");
            Thread.sleep(2000);
            //Quay lại trang Github
            String GGwindow=driver.getWindowHandle();
            //Switch về Github
            switchToWindowByID(GGwindow);
            System.out.println(driver.getWindowHandle());

        //Sang trang Facebook

            driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
            Thread.sleep(2000);
            switchToWindowByTitle("Facebook – log in or sign up" );
            Thread.sleep(2000);
            System.out.println("FacebookWindowID="+driver.getWindowHandle());

            switchToWindowByTitle("Selenium WebDriver");
            Thread.sleep(2000);
            String GithubID=driver.getWindowHandle();
            System.out.println("GitWindowID="+driver.getWindowHandle());
        //Sang Tiki
            driver.findElement(By.xpath("//a[text()='TIKI']")).click();
            Thread.sleep(2000);
            switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
            Thread.sleep(5000);
            Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
            Thread.sleep(2000);

            CloseAllExceptMainWindow(GithubID);
            //Close xong phải quay về tab chính vì nó vẫn ở Tab cuối cùng
            driver.switchTo().window(GithubID);


        }
//Close All Tab ngoại trừ Tab chính: bỏ break đi để quét hết Tab
    private void CloseAllExceptMainWindow(String GithubID) throws InterruptedException {
        Set<String> allWindow=driver.getWindowHandles();
        for(String otherWindow:allWindow){
            //Nếu WindowID khác GithubID thì close Tab
            if(!otherWindow.equals(GithubID)){
                driver.switchTo().window(otherWindow);
                Thread.sleep(2000);
                driver.close();
                Thread.sleep(2000);
                //Ko dùng break để quét hết các Tab
            }
        }
    }

    //Chỉ có thể dùng cho duy nhất 2 window
    private void switchToWindowByID(String GitWindow) {
        Set<String> allWindows=driver.getWindowHandles();
        for(String googlewindow:allWindows){
            if(!googlewindow.equals(GitWindow)){
                driver.switchTo().window(googlewindow);
            break;
            }
        }
    }

//Dùng cho từ 2 Tab trở lên(All trường hợp đều dùng đc)
private void switchToWindowByTitle(String expectedTitle) {
    //Lấy ra windowID cửa 2 cứa sổ/Tab
    Set<String> allWindows=driver.getWindowHandles();
    //Duyệt qua từng ID
    for(String window:allWindows){
        //Switch trước vào từng Window
        driver.switchTo().window(window);
        //Lấy ra pageTitle đang active
        String pageTitle=driver.getTitle();
        //Nếu pageTitle hiện tại bằng vs Title mong đợi=> thì break luôn
        if(pageTitle.equals(expectedTitle)){
            break;
        }
    }

}

    @Test
        public void TC_02_TechPanda() throws InterruptedException {
            driver.get("http://live.techpanda.org/");

            //Lấy ra WindowID page hiện tại
            String WindowMainID=driver.getWindowHandle();
            driver.findElement(By.xpath("//a[text()='Mobile']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
            Thread.sleep(2000);
            //Verify text 'Sony Xperia' được add vào màn hình
            Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

            //Add Iphone to Compare
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product IPhone has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(2000);
            //Lấy ra 2 WindowID
            Set <String> allWindows=driver.getWindowHandles();
            for(String window:allWindows){
                if(!window.equals(WindowMainID)){
                    driver.switchTo().window(window);
                    break;
                }
            }
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        //Close tab:
        driver.close();
        Thread.sleep(1000);
        //Chuyển về parent window
        driver.switchTo().window(WindowMainID);
        //Click 'Clear All'
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        //Verify message: 1-click [OK]ở pop-up/ 2-verify text

        Alert alert=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(2000);
        //Verify text sau clear All product
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");
        }

        @Test
        public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        String mainWindow=driver.getWindowHandle();
        System.out.println(mainWindow);
        //Click vào button Login
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        //Chuyển sang Window Login
        Set <String> allWindow=driver.getWindowHandles();
        for(String loginWindow:allWindow){
            //Cách dùng windowID
            if(!loginWindow.equals(mainWindow)){
                driver.switchTo().window(loginWindow);
            }
        }
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        //Verify err-message
        By emailError =By.xpath("//h2[text()='Log in with your email account']/following-sibling::div[contains(@class,'gigya-composite-control-textbox')]/span");

        Assert.assertEquals(driver.findElement(emailError).getText(),"This field is required");

        By passwordError =By.xpath("//h2[text()='Log in with your email account']/following-sibling::div[contains(@class,'gigya-composite-control-password')]/span");

        Assert.assertEquals(driver.findElement(emailError).getText(),"This field is required");
        Thread.sleep(2000);

        driver.close();
        Thread.sleep(2000);

        driver.switchTo().window(mainWindow);

        //Click vào ô search
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("Automation");
        driver.findElement(By.cssSelector("button[type='submit']>i.i-search")).click();
        }

    @Test
    public void TC_04_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String mainWindow = driver.getWindowHandle();
        System.out.println("MainwindowID="+mainWindow);
        //Click vào login
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(2000);


        Set<String> allWindows = driver.getWindowHandles();
        for (String loginwindow : allWindows) {
            //Switch vào từng window để lấy title page
            //Ở bài này, do witch vào để kiểm tra title, nên window thứ 2 nó bị close(khi move sang window1), do vậy:
            //nó ko tìm thấy window2(bị close rùi)=> bài này chỉ nên dùng windowID
            driver.switchTo().window(loginwindow);
            Thread.sleep(2000);
            //Lấy ra title của page đang active
            String currenttitlepage = driver.getTitle();
            //Check if trong window vừa switch vào: nếu title page = TitleloginWindow thì click vào HarvardKey button
            if (currenttitlepage.equals("Harvard Division of Continuing Education Login Portal")) {
                //tìm thấy thì thoaát ra luôn
                break;
            }
        }
//            Assert.assertEquals(driver.getTitle(), "Harvard Division of Continuing Education Login Portal");
//            driver.findElement(By.cssSelector("button#harvard-key-button")).click();
//            Thread.sleep(2000);

//            if (!loginwindow.equals(mainWindow)) {
//                driver.switchTo().window(loginwindow);
//                System.out.println("loginWindowID="+driver.getWindowHandle());
//            }
//        }
        //Close allTab trừ tab chính
        for (String loginwindow : allWindows) {
            if(!loginwindow.equals(mainWindow)){
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        Thread.sleep(2000);
        //Check errAuthenText is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Authentication was not successful.  Please try again.']")).isDisplayed());
    }

    @Test
    public void TC_windowTab_selenium_4x(){
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
        //Qua trang Admin-> quay về trang window của user
        //Tự switch qua ko cần sử dung windowID nữa, có thể test song song
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.nopcommerce.com/en/demo");
    }

    @Test
    public void TC_study_01(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //Lấy ra ID của curent Window
        String GithubWindowID=driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        Set<String> allWindowIDs=driver.getWindowHandles();

        for(String id:allWindowIDs){
            if (!id.equals(GithubWindowID)){
                driver.switchTo().window(id);
            }
        }
       driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Việt Nam");
        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys(Keys.ENTER);
    }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
