package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_Browser_Exercise {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

        @Test
        public void TC_01_Displayed() {
            driver.get("https://automationfc.github.io/basic-form/index.html");
            if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
                driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
                System.out.println("Email textbox is dispalyed");
            } else{System.out.println("Email textbox is not displayed");

            };

            if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
                driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
                System.out.println("Education textbox is displayed");
            } else {System.out.println("Education textbox is not displayed");}

            if(driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed()){
                driver.findElement(By.xpath("//label[text()='Under 18']")).click();
                System.out.println("Radio Age under 18 is displayed");
            }else {System.out.println("Radio Age under 18 is not displayed");}

            driver.findElement(By.xpath("//img[@alt='User Avatar 05']")).click();
            if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
                System.out.println("Title User5 is displayed");
            } else System.out.println("Title User5 is not displayed");

        }

        @Test
        public void TC_02_Enabled() {
            driver.get("https://automationfc.github.io/basic-form/index.html");
            if(driver.findElement(By.cssSelector("input#mail")).isEnabled()){
                System.out.println("Email is enabled");
            }else {System.out.println("Email is disable");}

            if(driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled()){
                System.out.println("Radio Age Under 18 is enabled");
            } else {System.out.println("Radio Age Under 18 is disabled");}

            if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
                driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
                System.out.println("Education textbox is enable");
            } else {System.out.println("Education textbox is disabled");}

            if(driver.findElement(By.cssSelector("select#job1")).isEnabled()){
                System.out.println("Job Role 1 is enable");
            } else {System.out.println("Job Role 1 is disabled");}

            if(driver.findElement(By.cssSelector("select#job2")).isEnabled()){
                System.out.println("Job Role 2 is enable");
            } else {System.out.println("Job Role 2 is disabled");}

            if(driver.findElement(By.xpath("//input[@id='development']")).isEnabled()){
                System.out.println("Developer checkbox is enable");
            } else {System.out.println("Developer checkbox is disabled");}

            if(driver.findElement(By.xpath("//label[text()='Slider 01:']")).isEnabled()){
                System.out.println("Developer checkbox is enable");
            } else {System.out.println("Developer checkbox is disabled");}
            //Step3:
            if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()){
                System.out.println("Password checkbox is enable");
            } else {System.out.println("Password checkbox is disabled");}

            if(driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()){
                System.out.println("Radio button is enable");
            } else {System.out.println("Radio button is disabled");}

            if(driver.findElement(By.cssSelector("textarea#bio")).isEnabled()){
                System.out.println("Biography is enable");
            } else {System.out.println("Biography is disabled");}

            if(driver.findElement(By.cssSelector("select#job3")).isEnabled()){
                System.out.println("Job Role 3 is enable");
            } else {System.out.println("Job Role 3 is disabled");}

            if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()){
                System.out.println("Checkbox Interest is enable");
            } else {System.out.println("Checkbox Interest is disabled");}

            if(driver.findElement(By.cssSelector("input#slider-2")).isEnabled()){
                System.out.println("Slider 2 is enable");
            } else {System.out.println("Slider 2 is disabled");}
        }

        @Test
        public void TC_03_Selected() {
            driver.get("https://automationfc.github.io/basic-form/index.html");
            //Click chọn
            driver.findElement(By.xpath("//input[@id='under_18']")).click();
            driver.findElement(By.cssSelector("input#java")).click();
            //Kiểm tra isSelected
            if(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected()){
                System.out.println("Radio button is selected");
            }else {System.out.println("Radio button is de-selected");}

            if(driver.findElement(By.cssSelector("input#java")).isSelected()){
                System.out.println("Java checkbox is selected");
            }else {System.out.println("Java checkbox is de-selected");}
            //Click lần 2
            driver.findElement(By.xpath("//input[@id='under_18']")).click();
            driver.findElement(By.cssSelector("input#java")).click();
            //Kiểm tra isSelected
            if(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected()){
                System.out.println("Radio button is selected");
            }else {System.out.println("Radio button is de-selected");}
            //Bỏ chọn Language:Java
            if(driver.findElement(By.cssSelector("input#java")).isSelected()){
                System.out.println("Java checkbox is selected");
            }else {System.out.println("Java checkbox is de-selected");}

        }

    @Test
    public void TC_04_Displayed_Enabled_Selected() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotesting@gmail.com");
        //Empty
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check not-completed']")).isDisplayed());
        Thread.sleep(10000);
        //Lowercase
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("sele");
        //hàm scrollBy của js
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)", "");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Thread.sleep(10000);

        //Uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("SELE");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Thread.sleep(10000);


        //Specialcase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!!@$");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Thread.sleep(10000);


        //Username
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Thread.sleep(10000);

        //8-chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTOMATION");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Thread.sleep(10000);

        //One-number
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("1234");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Thread.sleep(10000);

        //Full
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Selenium123@");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();

//        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Thread.sleep(10000);

//isSelected
        //hàm scrollBy của js
        JavascriptExecutor js_test = (JavascriptExecutor) driver;
        js_test.executeScript("window.scrollBy(0,1080)", "");
        driver.findElement(By.cssSelector("input[name='marketing_newsletter']")).click();
        if (driver.findElement(By.cssSelector("input[name='marketing_newsletter']")).isDisplayed()){
            System.out.println("Checkbox is selected");
        }else System.out.println("checkbox is de-selected");
    }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
