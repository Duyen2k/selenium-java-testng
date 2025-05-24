package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_17_InterActions_P2 {

    WebDriver driver;
    Actions action;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        driver=new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        action=new Actions(driver);
    }

        @Test
        public void TC_01_ClickAndHold_Fixed() {
            driver.get("https://automationfc.github.io/jquery-selectable/");
            //Click chọn 1 ô đầu tiên
            //Di chuột đến ô cuối muốn chọn
            //Thả chuột

            List<WebElement> numbers =driver.findElements(By.cssSelector("ol#selectable li"));

            action.clickAndHold(numbers.get(4))    //Click và giữ chuột ở số 5 (index=n-1)
                    .pause(Duration.ofSeconds(2))  //Đợi 2s
                    .moveToElement(numbers.get(11)) //Di chuyển đến ô số 12
                    .release()   //thả chuột
                    .perform();

            List <WebElement> numberSelected= driver.findElements(By.cssSelector("li.ui-selected"));

            Assert.assertEquals(numberSelected.size(),8);

        }

        @Test
        public void TC_02_Random() {
            driver.get("https://automationfc.github.io/jquery-selectable/");

            List <WebElement> numberlist=driver.findElements(By.cssSelector("ol#selectable li"));
            //Phím Ctrl này chỉ có trên Windown vì vậy có thể check nếu alf windown thì chaỵ đc

//            String osName =System.getProperty("os.name");

            //List ra những số muốn lấy: 1,3,6,11 cho vào 1 giỏ
            List <String> expectedNumber= new ArrayList<String>();
            expectedNumber.add("1");
            expectedNumber.add("3");
            expectedNumber.add("6");
            expectedNumber.add("11");

            //Khai báo phím Control
            Keys key= Keys.CONTROL;

            action.keyDown(key).perform();
            //Chọn số 1,3,6,11
            action.click(numberlist.get(0))
                    .pause(Duration.ofSeconds(1))
                    .click(numberlist.get(2))
                    .click(numberlist.get(5))
                    .click(numberlist.get(10))
                    .perform();

            action.keyUp(key).perform();

            List <WebElement> selectedNumber=driver.findElements(By.cssSelector("li.ui-selected"));
            Assert.assertEquals(selectedNumber.size(),4);

            //Chạy xong rồi thì bỗ ra những thằng đã được chọn cho vào 1 giỏ

            List <String> actualNumber= new ArrayList<String>();
            //Chạy vòng lặp for để list ra giá trị đã được chọn
            for (WebElement number:selectedNumber){
                //Cho vào giỏ actualNumber
                actualNumber.add(number.getText());
            }

            Assert.assertEquals(expectedNumber,actualNumber);

            System.out.println(actualNumber);
        }


    @Test
    public void TC_03_DoubleClick() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //Firefox mới phải kéo lên vì nó chỉ thao tác đến phần maf user nhìn thấy
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//legend[text()='Double Click']/following-sibling::p")).getText(),"Hello Automation Guys!");

    }

    @Test
    public void TC_04_Right_ClicktoElemet() throws InterruptedException {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover')]")).isDisplayed());
        Thread.sleep(3000);
        action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover')]"))).perform();
        //Accept pop-up
        Alert alert=new WebDriverWait(driver,Duration.ofSeconds(3)).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        //Quit biến mất
        Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());
    }


    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
