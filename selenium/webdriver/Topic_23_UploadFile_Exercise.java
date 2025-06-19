package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_23_UploadFile_Exercise {

    WebDriver driver;
    String UploadfilePath= System.getProperty("user.dir")+"\\selenium\\UploadFile\\";
    String LemonName= "LemonIMG.jpg";
    String PeonyName= "PeonyIMG.jpg";
    String SantoriniName= "SantoriniIMG.jpg";

    String LemonPath=UploadfilePath+LemonName;
    String PeonyPath=UploadfilePath+PeonyName;
    String SantoriniPath=UploadfilePath+SantoriniName;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_() throws InterruptedException {
            driver.get("https://blueimp.github.io/jQuery-File-Upload/");
            //Ko quan trọng btn Upload ẩn hay hiện ->đèu có thể upload bằng caách SendKey
            By AddfileBTN= By.xpath("//input[@type='file']");

            driver.findElement(AddfileBTN).sendKeys("D:\\Automation course\\02. Selenium WebDriver\\selenium-java-testng\\selenium\\UploadFile\\LemonIMG.jpg");
            driver.findElement(AddfileBTN).sendKeys(PeonyPath);
            driver.findElement(AddfileBTN).sendKeys(SantoriniPath);


        List < WebElement> uploadBTN=driver.findElements(By.xpath("//span[text()='Start']"));
        for (WebElement upload:uploadBTN){
            upload.click();
            Thread.sleep(2000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+LemonName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+PeonyName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+SantoriniName+"']")).isDisplayed());
        }

        @Test
        public void TC_02_() {
            driver.get("https://www.facebook.com/");
        }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
