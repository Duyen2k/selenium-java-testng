package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Custom_Checkbox_Radio {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Ubuntu() throws InterruptedException {
            driver.get("https://login.ubuntu.com/");

            //Case 1: Thẻ bị đè label lên
            //Thẻ input ko click lên được nhưng dùng verify được
            //driver.findElement(By.cssSelector("input#id_new_user")).click();
            //Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

            //Case 2:
            //Không dùng thẻ input để click
            //Dùng thẻ khác để thay thế
            //Thẻ khác này lại ko thể dùng verìy được
            //driver.findElement(By.cssSelector("label.new-user")).click();
            //Thread.sleep(3000);
            //Veriy isSelected() thì thẻ ;abel/span/li/ul/... trạng thái selected luôn bằng false
            //Assert.assertTrue(driver.findElement(By.cssSelector("label.new-user")).isSelected());

            //Case 3: Dùng thẻ label để click và dùng thẻ Input để verify
            //Vấn đề: 1 element dùng 2 locator thì mất nhiều tgian nếu sau có maintain => dùng 1 element để vừa click và verify
            //driver.findElement(By.cssSelector("label.new-user")).click();
            //Thread.sleep(2000);
            //Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

            //Case 4: Dùng 1 element để vừa click và vừa verify
            By RegistedRadio=By.cssSelector("input#id_new_user");
            //Js này ko chính thống nên chỉ dùng khi Selenium ko hỗ trợ(hạn chế sử dụng vì and user ko sdung)
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(RegistedRadio));
            Assert.assertTrue(driver.findElement(RegistedRadio).isSelected());

        //Custom Checkbox
            By RegistedCheckbox=By.cssSelector("input#id_accept_tos");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(RegistedCheckbox));
            Assert.assertTrue(driver.findElement(RegistedCheckbox).isSelected());

        }

        @Test
        public void TC_02_Google_Form() throws InterruptedException {
            driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
            Thread.sleep(2000);
            By HanoiRadio=By.cssSelector("div[data-value='Hà Nội']");
            //Kiểm tra Radio chưa được chọn
            org.testng.Assert.assertEquals(driver.findElement(HanoiRadio).getDomAttribute("aria-checked"),"false");
            //Click vào Radio
            driver.findElement(HanoiRadio).click();
            Thread.sleep(2000);
            //Verify bằng isSelected() => ko nên dùng vì phải definite thêm element
            Assert.assertTrue(driver.findElement(By.cssSelector("div[data-value='Hà Nội'][aria-checked='true']")).isDisplayed());
            //Verify bằng getDomAttribute: vì khi click xong nó được hiển thị 'true'
            //Vì là dạng custom nên ko dùng isSelected
            org.testng.Assert.assertEquals(driver.findElement(HanoiRadio).getDomAttribute("aria-checked"),"true");
//      Checkbox
            By MiquangCheckbox=By.cssSelector("div[aria-label='Mì Quảng']");
            //Kiem tra chưa chọn
            org.testng.Assert.assertEquals(driver.findElement(MiquangCheckbox).getDomAttribute("aria-checked"),"false");
            //Click chọn
            driver.findElement(MiquangCheckbox).click();
            Thread.sleep(2000);
            //Kiểm tra đã choọn
            org.testng.Assert.assertEquals(driver.findElement(MiquangCheckbox).getDomAttribute("aria-checked"),"true");

        //Select All checkbox
            List< WebElement> allcheckboxes=driver.findElements(By.cssSelector("div[role='checkbox']"));
            for (WebElement SetectedCheckbox: allcheckboxes){
                if(!SetectedCheckbox.isSelected()) {
                SetectedCheckbox.click();
                Thread.sleep(2000);
            }
            }
            for (WebElement SetectedCheckbox: allcheckboxes){
                org.testng.Assert.assertTrue(SetectedCheckbox.isDisplayed());
            }


        }

        @Test
        public void TC_() throws InterruptedException {
            driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
            Thread.sleep(2000);
            List< WebElement> allcheckboxes=driver.findElements(By.cssSelector("div[role='checkbox']"));
            for (WebElement SetectedCheckbox: allcheckboxes){
                if(!SetectedCheckbox.isSelected()) {
                    SetectedCheckbox.click();
                    Thread.sleep(2000);
                }
            }
            for (WebElement SetectedCheckbox: allcheckboxes){
                //Vì là dạng custom nên ko dùng isSelected()
                org.testng.Assert.assertTrue(SetectedCheckbox.isDisplayed());
            }
        }
    @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }
