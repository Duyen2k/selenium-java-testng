package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_20_Frame_Iframe {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_GoogleMap() throws InterruptedException {
        //A(cha) chứa iframe B
            driver.get("https://embedgooglemap.net/");
            String textA=driver.findElement(By.cssSelector("input#s")).getDomAttribute("value");
            System.out.println(textA);
        //Đi vào iframe B
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.gmap_canvas iframe")));

        //Đi vào iframe C
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#mapDiv>div>div.gm-style>iframe")));
        //Từ C quay lại B
        driver.switchTo().parentFrame();
        //Từ B quay lại A
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input#s")).clear();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#s")).sendKeys("121 bà triệu,hà nội");

        }

        @Test
        public void TC_02_Toidicodedao() throws InterruptedException {
            driver.get("https://toidicodedao.com/");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("aside#facebook-likebox-3")));
            Thread.sleep(2000);
            //Switch vào iframe=> page này ko cần vào iframe B
//            By iframeB=By.cssSelector("div.fb-page iframe");
//            driver.switchTo().frame(driver.findElement(iframeB));
            driver.findElement(By.xpath("//a[text()='Like ngay để không bỏ lỡ những bài viết hay nhé!']")).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Tôi đi code dạo']")).isDisplayed());
        }

        @Test
        public void TC_03_Fillform() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        Thread.sleep(2000);
        //Switch vào iframe B
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        //Chọn dropdown Year
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Junior");
        //Chọn dropdown Residence
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("East Dorm");
        //Chọn Gender
            By gender=By.cssSelector("label[for='RESULT_RadioButton-4_0']");
        if(!driver.findElement(gender).isSelected()){
            driver.findElement(gender).click();
        }
        //Click submit button
        driver.findElement(By.name("Submit"));
        //Từ iframe B về A
        driver.switchTo().defaultContent();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div.details__text-wrapper")));
            Thread.sleep(2000);
        //Tắt pop-up
         driver.findElement(By.cssSelector("button.osano-cm-dialog__close")).click();
         Thread.sleep(2000);
        //Click vào Login button
        driver.findElement(By.cssSelector("nav.js-header div.container>a[title='Log in']")).click();

        //Click vào Login trong form
        driver.findElement(By.cssSelector("button#login")).click();
        Thread.sleep(2000);
       //Verify error message
       Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
        }

    @Test
    public void TC_04_TinyCloud() throws InterruptedException {
        driver.get("https://www.tiny.cloud/docs/tinymce/latest/basic-example/");
        //Switch vào iframe B
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#basic-example_ifr")));
        //Nhập text vào ô trong iframe
        driver.findElement(By.cssSelector("body#tinymce")).clear();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body#tinymce")).sendKeys("Automation testing");
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.cssSelector("body#tinymce>p")).getText());
        //Quay về A
        driver.switchTo().defaultContent();
        //Nhập vào ô search text'create a plugin'
        driver.findElement(By.cssSelector("button.DocSearch-Button")).click();
        driver.findElement(By.cssSelector("input#docsearch-input")).sendKeys("Testing TinyMCE");
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[role='listbox']")));
    }

    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
