package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_13_Parallel_Method {
    WebDriver driver;

    @BeforeMethod
    public void beforeclass(){
        driver=new FirefoxDriver();

    }
    //Chạy sẽ skip case-3

    @Test
    public void TM_01_register(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        Assert.assertFalse(driver.findElement(By.cssSelector("button[title='Login']")).isDisplayed());

    }

    @Test(dependsOnMethods = "TM_01_register")
    public void TM_02_login(){
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

    }
   //Set enable để ko chạy-skip
    @Test(dependsOnMethods ={"TM_01_register","TM_02_login"})
    public void TM_03_order(){
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

    }

    @Test
    public void TC_04_pay(){

    }

    @Test
    public void TC_05_ship(){

    }

    @AfterMethod
    public void afterclass(){

    }
}
