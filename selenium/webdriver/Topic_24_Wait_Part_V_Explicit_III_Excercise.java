package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_Part_V_Explicit_III_Excercise {

    WebDriver driver;
    WebDriverWait explicitWait;
    //Dùng Explicit trong TH kết hợp với các hàm khác



    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_07_upload_Mix_explicit() throws InterruptedException {
        driver.get("https://gofile.io/home");
    //Chờ cho loading icon hiện ra hết (đến khi nó biến mất)
        explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filemanager_loading")));
    //Muốn upload cứ sendkey vào thằng input
        String systemPath=System.getProperty("user.dir")+"\\selenium\\UploadFile\\";
        String PeonyName="PeonyIMG.jpg";
        String LemonName="LemonIMG.jpg";
        String SantoriniName="SantoriniIMG.jpg";

        String PeonyPath=systemPath+PeonyName;
        String LemonPath=systemPath+LemonName;
        String SantoriniPath=systemPath+SantoriniName;

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(PeonyPath +"\n"+LemonPath+"\n"+SantoriniPath);

    //Đợi tất cả các ảnh được upload lên
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#fileList div[class='progress-container']"))));

    //Loading icon cuả ảnh lần 2
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'item-mediaplayer')]/div[contains(@class,'animate-spin')]")));

    //Hiển thị Upload Complete (hoặc dùng textTobe, sau đó AssertEquals text)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Upload Complete']")));

    String link= driver.findElement(By.cssSelector("a.linkSuccessCard")).getDomAttribute("href");
    //Bấm vào link
        Thread.sleep(2000);
        driver.get(link);
        Thread.sleep(2000);
    //Đợi loading icon bien mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filemanager_loading")));

    //Đợi 3 picture hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='truncate']/a[text()='"+PeonyName+"']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='"+PeonyName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='"+LemonName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='"+SantoriniName+"']")).isDisplayed());

    //Đợi cho button play/download xuất hiện tại 3 picture
        //explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(@class,'item_play')]")));
        //explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(@class,'item_download')]")));





    }


    @AfterMethod
        public void afterClass(){
//            driver.quit();
        }
    }
