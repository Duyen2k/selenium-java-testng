package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Alway_Run {
    WebDriver driver;

    @BeforeClass
    public void beforeclass(){
    driver=new FirefoxDriver();
    driver.get("https://demo.nopcommerce.com/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    Assert.assertTrue(false);
    System.out.println("----Run Passed Before Class----'");
    }

    //Cách 1: đánh priority
    //Cách 2: Đánh trực tiếp vào tên TC(vì nó chạy ưu tiên số ASC-sau đó đến chữ alphabet)
    @Test(priority = 1)
    public void TC_01_register(){

    System.out.println("----Action and Verify----'");
    }

    @AfterClass(alwaysRun = true)
    public void afterclass(){
        System.out.println("----Run Passed After Class----'");
    }
}
