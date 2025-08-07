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

public class Topic_24_Wait_Part_V_Explicit_I {

    WebDriver driver;
    WebDriverWait explicitWait;
    //Dùng Explicit trong TH kết hợp với các hàm khác

    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test()
    public void TC_00_() throws InterruptedException {
    //**Một số hàm hay dùng

    //1.Chờ cho một Alert được xuất hiện trong html + sau đó switch vào
    explicitWait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();

    //2.Element clickable (Button/checkbox/Radio/Link/Image,...)
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
    driver.findElement(By.cssSelector("")).click();

    //3.Element visible(All element)
    //3.1 Get Text/get Css/Atribute/Displayed/...
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
    Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

    //3.2 Element selected (Checkbox/Radio)
    explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
    Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());

    //4.Invisible (All element)
    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
    Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

    //5. Presence (All element in HTML)
    explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

    //6.Element size
    explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),15));
        //Trước đó phải dùng hàm để tìm ra đúng size của element
    Assert.assertEquals(driver.findElement(By.cssSelector("")).getSize(),15);

    //7.Atribute
    explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"value","John"));
    Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("value"),"John");

    //8.Text
    explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"Selenium"));
    Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"");
    }


    @AfterMethod
        public void afterClass(){
//            driver.quit();
        }
    }
