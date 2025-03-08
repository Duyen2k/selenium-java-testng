package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Topic_05_WebBrowser_Commands {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Driver ID="+driver.toString());
    }

    @Test
    public void TC_01_() throws MalformedURLException {
        driver.get("https://www.facebook.com/"); //**
//        driver.findElement(By.cssSelector("input#pass"));
        driver.quit(); //đóng toàn bộ //**
//        driver.close(); //đóng tab hiêện tại

    //Tìm một element vs locator là tham số truyền vào
    driver.findElement(By.cssSelector("")); //**

    //Tìm nhiều elements vs locator là tham số truyền vào
    driver.findElements(By.cssSelector("")); //**

    //Lấy ra Url của homepage hiển tại
//     String homePageUrl= driver.getCurrentUrl();

    //Sủ dụng luôn ko cần lưu trữ
//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

    //Lấy ra Url ở page hiện tại:
    driver.getCurrentUrl();
    System.out.println("Page URL=" + driver.getCurrentUrl());
    // Lấy title của page hiêện tại
    driver.getTitle();
    System.out.println("Page Title=" + driver.getTitle());

    //Lấy ra Window ID của page hiện tại:test các case tương tác nhiều tab
    driver.getWindowHandle();
    System.out.println("Window ID=" + driver.getWindowHandle());
    //Lấy ra tất cả các window ID các tab/browser
    driver.getWindowHandles();
    System.out.println("Window ID=" + driver.getWindowHandles());
    //Lấy ra source code của page hiện tại: là cái lấy ở view page source
    driver.getPageSource();
    System.out.println("Page Source Code=" + driver.getPageSource());
    WebDriver.TargetLocator switchTo=driver.switchTo();
    //Alert- Frame/iframe -Window/Tsb
    //Alert
    driver.switchTo().alert(); //**
    //Frame-iframe
        //switch vào frame/iframe
    driver.switchTo().frame(""); //**
        //Quay lại trang ngoài
    driver.switchTo().defaultContent();
        //Từ frame con ra frame cha( nhiều frame lồng nhau)
    driver.switchTo().parentFrame();
    //window -Tab
    driver.switchTo().window(""); //**
    driver.switchTo().newWindow(WindowType.TAB).get("https://live.techpanda.org/");
    driver.switchTo().newWindow(WindowType.WINDOW).get("https://live.techpanda.org/");

    //Set timeout để ti element
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
    driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(30));
    driver.manage().timeouts().implicitlyWait(Duration.ofHours(30));  //ko tìm thấy sẽ chờ 30 giwof mới báo kqua

    //Add cookie hoặc get cookie,VD lưu cookie để bỏ qua login trên giao diện cho nhanh
    driver.manage().getCookies();
//    driver.manage().addCookie();

    //Brower: fullscreen/maximum/minimum
    driver.manage().window().fullscreen();
    driver.manage().window().maximize(); //**
    driver.manage().window().minimize();

    //Set brower có kich thước bằng bao nhiêu (Responsive)
    driver.manage().window().setSize(new Dimension(1920,1080));
    driver.manage().window().setSize(new Dimension(1366,768));

    //Set browser tại vị trí nào:
    driver.manage().window().setPosition(new Point(0,0));
    //Selenium Log: Browser/Driver/Network
    driver.manage().logs().get(LogType.BROWSER);
    driver.manage().logs().get(LogType.PERFORMANCE);
    driver.manage().logs().get(LogType.CLIENT);
    driver.manage().logs().get(LogType.SERVER);
    driver.manage().logs().getAvailableLogTypes();

    //Quay lại trang trước đó:
    driver.navigate().back();
    //Chuyển tiếp đến trang trước đó (trên đầu page để back/forward trang):
    driver.navigate().forward();
    //Refresh lại trang hiện tại
    driver.navigate().refresh();

    //Mở ra 1 cái url
    driver.navigate().to("https://www.youtube.com/?app=desktop&hl=vi");
    driver.navigate().to( new URL("https://www.youtube.com/?app=desktop&hl=vi"));


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
