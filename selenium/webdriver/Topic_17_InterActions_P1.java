package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_InterActions_P1 {

    WebDriver driver;
    Actions action;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        driver=new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        action=new Actions(driver);
    }

        @Test
        public void TC_01_ToolTips() {
            driver.get("https://automationfc.github.io/jquery-tooltip/");
            //Một số haàm hay dùng cho hover
            //moveToElement(): hover chuột,perform():quan trọng để chay
            //Lưu ý: chạy action ko dùng chuột hay bàn phím để ko bị move hover ra ngoài

            //Step:
            //Di chuột đến nơi hiện tooltip
            //Pause lại 3s
            //Thực thi
            //Dùng F8 để bật debug sau đó verify
            action.moveToElement(driver.findElement(By.cssSelector("input#age")))
                .pause(Duration.ofSeconds(3)).perform();
            Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        }

        @Test
        public void TC_02_Myntra() {
            driver.get("https://www.myntra.com/");
            action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']")))
                    .pause(Duration.ofSeconds(3)).perform();
            driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");

        }
        //tips: Nếu chạy mà page chặn ko cho chạy auto thì cần add thêm config để chạy qua profile vào before class: cách lấy: edge://version/
        // EdgeOptions edgeOptions=new EdgeOptions();
        //edgeOptions.addArguments("--user-data-dir=C:/Users/daomi/AppData/Local/Microsoft/Edge/User Data/");
        // edgeOptions.addArguments("--profile-directory=Profile

    @Test
    public void TC_03_Fahasha() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(7000); //pop-up hện lên thì close pop-up (giải pháp tạm thời)
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu")))
                .pause(Duration.ofSeconds(3)).perform();

        action.moveToElement(driver.findElement(By.xpath("//ul[contains(@class,'navbar-nav')]//span[text()='FOREIGN BOOKS']")))
                .pause(Duration.ofSeconds(3)).perform();

        action.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Economics']"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Economics']")).isDisplayed());
    }


    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
