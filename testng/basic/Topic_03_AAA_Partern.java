package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_AAA_Partern {

    //Arrange: Arrage khai báo/Action: tổng hợp dữ liệu/ Assert: verify dữ liệu
    //Setup/ Initial Data/ Browser/ Driver/ Variable/ DB/...

    //Cụm Arrange(khai báo-khởi tạo dữ liệu)
    WebDriver driver;

    @BeforeClass
    public void beforeclass(){
    driver=new FirefoxDriver();
    driver.get("");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    //Action
    @Test
    public void register(){
    //-------Action------

    //Open Page

    //----Fill data-----

    //Assert: Verify data

    }

    @AfterClass
    public void afterclass(){

    }
}
