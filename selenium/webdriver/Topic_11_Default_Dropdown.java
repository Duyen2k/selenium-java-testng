package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Default_Dropdown {

    WebDriver driver;
    Select select;


    @BeforeClass
    public void beforeClass() {
        FirefoxOptions option=new FirefoxOptions();
        option.addPreference("geo.enabled",false);
        option.addPreference("geo.provider.use_corelocation",false);
        driver= new FirefoxDriver(option);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_() throws InterruptedException {
            driver.get("https://www.rode.com/wheretobuy");
            //Kiểm tra có được chọn nhiều hay ko
            select= new Select(driver.findElement(By.cssSelector("select#country")));
            Assert.assertFalse(select.isMultiple());

            select.selectByVisibleText("Vietnam");
            Thread.sleep(4000);
            //Verify text đã chọn
            Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");

            //Nhập text để tìm kiếm
            driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
            driver.findElement(By.xpath("//button[text()='Search']")).click();
            Thread.sleep(4000);

            //In ra list data trong droplist
            List< WebElement> DealerBranches= driver.findElements(By.cssSelector("div.dealer_branch h4"));
            Assert.assertEquals(DealerBranches.size(),16);
            //Vòng lặp for-each
            for(WebElement dealerName: DealerBranches){
                System.out.println(dealerName.getText());
            }
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
