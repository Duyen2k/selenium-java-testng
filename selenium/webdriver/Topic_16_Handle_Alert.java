package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Handle_Alert {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_() {
            driver.get("https://www.facebook.com/");

        //Chờ 1 alert xuất hiện trong HTML (alertIsPresnet là của JS nên sẽ check có trong DOM hay chưa)=> Nên dùng
        Alert alert=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()) ;
        //Accept Alert ko dùng wait(nên dùng hàm Wait vì nó sẽ đợi alert xuất hiện, còn switchTo có thể vào ko thấy alert sẽ bị Fail)
        driver.switchTo().alert().accept();
        //Cancel Alert
        driver.switchTo().alert().dismiss();
        //get text ra de verify
        driver.switchTo().alert().getText();
        //Nhập text rồi accept
        driver.switchTo().alert().sendKeys("");
        }

        @Test
        public void TC_01_Accept_Alert() throws InterruptedException {
            driver.get("https://automationfc.github.io/basic-form/index.html");
            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
            Thread.sleep(2000);
            Alert alert=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals(alert.getText(),"I am a JS Alert");
            alert.accept();
            Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

        }

        @Test
        public void TC_02_JS_Confirm_Alert() throws InterruptedException {
            driver.get("https://automationfc.github.io/basic-form/index.html");
            driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
            Thread.sleep(2000);
            Alert alert=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals(alert.getText(),"I am a JS Confirm");
            Thread.sleep(2000);
            //Click [OK] ở pop-up Confirm
            alert.accept();
            Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Ok");
            //Click [Cancel] ở pop-up Confirm
            driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
            Thread.sleep(2000);
            alert.dismiss();
            Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
        }

        @Test
        public void TC_03_Prompt_Alert() throws InterruptedException {
            driver.get("https://automationfc.github.io/basic-form/index.html");
            driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
            Thread.sleep(2000);
            Alert alert=new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals(alert.getText(),"I am a JS prompt");

            String name="DuyentestAuto";
            alert.sendKeys(name);
            Thread.sleep(2000);
            alert.accept();
            Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: "+name);
        }

    //Handle Authentication Alert
        @Test
        public void TC_04_Direct_Authen() throws InterruptedException {
            //TH1:Hiện luôn Authen Alert -> Truyền thằng username và password vào URL
            String username = "admin";
            String password = "admin";
            //Cú pháp nhập usernaem và oassword vào url link: "https:"+"//"+"username"+":"+"password"+"vế còn lại của url"
            driver.get("https://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");
        }
    @Test
    public void TC_05_Authen_Split_chuỗi() throws InterruptedException {
        //TH2; Từ 1 màn hình, bấm link để qua màn Authen Alert
        //Lấy link ra rồi truyền username+password
        //Viết hàm để chèn User+pass vào link: hàn split("")
        String username = "admin";
        String password = "admin";
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000);
        String authenlink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");
        driver.get(passtoAuthenLink(authenlink, username, password));
    }

        public String passtoAuthenLink(String authenlink,String username,String password){
        String[] text=authenlink.split("//");
        return text[0]+"//"+username+":"+password+"@"+text[1];
        }

        //Cách 3: Selenium version 4x

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
