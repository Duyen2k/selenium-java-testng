package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Shadow_DOM {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //Shadow ngăn chặn truy cập từ bên ngoài

        @Test
        public void TC_01_Shadow() throws InterruptedException {
            driver.get("https://automationfc.github.io/shadow-dom/");
        //Element ko nằm trong Shadow thì vẫn thao tác bình thường:
        String ScrollText= driver.findElement(By.xpath("//a[@href='scroll.html']")).getText();
        System.out.println(ScrollText);

        //Element nằm trong Shadow thì ko thao tác lên được bình thường (tìm trên màn hình cũng ko thể tìm)
//         driver.findElement(By.xpath("//div[@id='nested_shadow_host']//a[@href='scroll.html']")).getText();

        //Tìm element chứa shadow đầu tiên
            WebElement firstShadowHostElement =driver.findElement(By.cssSelector("div#shadow_host"));
        //Lấy ra shadow root đầu tiên
            SearchContext firstShadowroot = firstShadowHostElement.getShadowRoot();
        //Lấy ra text trong shadow, dùng bằng firstShadowroot chứ ko dùng driver
            String textShadow= firstShadowroot.findElement(By.cssSelector("span")).getText();
            System.out.println(textShadow);

            System.out.println(firstShadowroot.findElement(By.cssSelector("a")).getText());
        //Có thể thao tác bât kỳ vào Shadow DOM: VD: sendkeys
            firstShadowroot.findElement(By.cssSelector("input[type='text']")).sendKeys("selenium");
            Thread.sleep(2000);
        //tìm ra Shadow thứ 2(nằm trong shadow đầu tiên), thì khi đó shadowroot1 là shadowhost(cha) của thằng thứ 2
            WebElement secondShadowHostElement=firstShadowroot.findElement(By.cssSelector("div#nested_shadow_host"));
        //Lấy ra shadow thứ 2 nằm trong shadow 1
            SearchContext secondShadowroot= secondShadowHostElement.getShadowRoot();

           String textshadow2=secondShadowroot.findElement(By.cssSelector("div")).getText();
           System.out.println(textshadow2);

        }


        @Test
        public void TC_02_Appspot() throws InterruptedException {
            driver.get("https://books-pwakit.appspot.com/");
            Thread.sleep(5000);

            //Lấy ra shadow root đầu tiên:
            WebElement firstShadowhostElement=driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
            SearchContext firstShadowroot=firstShadowhostElement.getShadowRoot();

            firstShadowroot.findElement(By.cssSelector("app-toolbar>book-input-decorator>input")).sendKeys("Harry Potter");

            WebElement secondShadowhost=firstShadowroot.findElement(By.cssSelector("app-toolbar>book-input-decorator"));
            SearchContext secondShadowroot=secondShadowhost.getShadowRoot();
            secondShadowroot.findElement(By.cssSelector("div.icon")).click();
            Thread.sleep(2000);

            WebElement thirdShadowhost=firstShadowroot.findElement(By.cssSelector("main>book-explore"));
            SearchContext thirdShadowroot=thirdShadowhost.getShadowRoot();

            //Lấy ra title 20 thằng
            List <WebElement> forthShadowhostElements= thirdShadowroot.findElements(By.cssSelector("ul>li>book-item"));
            for (WebElement element: forthShadowhostElements){
                SearchContext shadowRoot=element.getShadowRoot();
                System.out.println(shadowRoot.findElement(By.cssSelector("div.title-container>h2")).getText());
            }
        }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
