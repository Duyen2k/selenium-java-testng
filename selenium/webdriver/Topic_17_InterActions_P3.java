package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_17_InterActions_P3 {

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
        public void TC_01_DragAndDrop_HTML4() {
            //WebElement
//            driver.findElement(By.cssSelector()).click();

            //Action
//            action.click(driver.findElement(By.cssSelector())).perform();

            //JS
//            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector()));

            //Tương tự với scrollToElement
            //Edge thì có thể dùng: action.scrollToElement
            //Firefox thì dùng js scrollInToView
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle=driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle=driver.findElement(By.cssSelector("div#droptarget"));

        Assert.assertEquals(bigCircle.getText(),"Drag the small circle here.");
        action.dragAndDrop(smallCircle,bigCircle).perform();

        Assert.assertEquals(bigCircle.getText(),"You did great!");
        //Kiểm tra color
            Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");

        }

        @Test
        public void TC_02_Drag_Drop_HTML5() {
            //Không nên làm auto test vì nó sẽ bị đánh giá là spam(sẽ có bài giải thích)
            //Chạy được với Chrome và Edge - ko support chạy được với FireFox:
            //nếu muốn chạy phải import thêm file của anh Đảm vào chạy theo js/jquery(hỗ trợ css): xem lại video 38
            driver.get("https://automationfc.github.io/drag-drop-html5/");

            WebElement SquareA=driver.findElement(By.cssSelector("div#column-a header"));
            WebElement SquareB=driver.findElement(By.cssSelector("div#column-b header"));

            action.dragAndDrop(SquareA,SquareB).perform();

            Assert.assertEquals(SquareA.getText(),"B");
            Assert.assertEquals(SquareB.getText(),"A");

        }


    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
