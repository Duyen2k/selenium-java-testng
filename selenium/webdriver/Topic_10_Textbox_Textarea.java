package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.Random;

public class Topic_10_Textbox_Textarea {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Registration() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        String firstname="duyen";
        String middlename="thi" ;
        String lastname="nguyen";
        String emailaddress="nguyenduyen"+ new Random().nextInt(9999)+"@gmail.net";
        String password="123123";
        String fullname1=firstname+" "+middlename+ " "+lastname;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstname);
        driver.findElement(By.cssSelector("input#middlename")).sendKeys(middlename);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastname);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailaddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("input#is_subscribed")).click();
        driver.findElement(By.cssSelector("button[title='Register']")).click();
//        driver.findElement(By.cssSelector("button#proceed-button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");

        String contactInfo=driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullname1));
        Assert.assertTrue(contactInfo.contains(emailaddress));
            Thread.sleep(3000);

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.cssSelector("input[value='5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good application\nPretty easy to navigate.");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Best Phone");
        WebElement Nickname=driver.findElement(By.cssSelector("input#nickname_field"));
        Nickname.clear();
        Nickname.sendKeys("Toiladuyenne");
        driver.findElement(By.cssSelector("div.buttons-set button")).click();
        String Message = driver.findElement(By.cssSelector("li.success-msg span")).getText();
        Assert.assertEquals(Message,"Your review has been accepted for moderation.");
    }

        @Test
        public void TC_02_Registration2() throws InterruptedException {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
            driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[text()='PIM']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
            Thread.sleep(2000);

            String firstname="Nguyen";
            String lastname="Hoa";
            String username="HoaNT"+ new Random().nextInt(99);
            String PassportNumber="123-123-1234-12";
            String Comment="This is generated data\\nof real people";


            driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstname);
            driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastname);


            driver.findElement(By.xpath("//input[@type='checkbox']/parent::label/span")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
            driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Auto@123");
            driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Auto@123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(10000);
            String EmployeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");

            Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstname);
            Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastname);
            Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),EmployeeID);
            Thread.sleep(5000);

//Step 8:
            driver.findElement(By.xpath("//a[text()='Immigration']")).click();
            driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div/button")).click();
            Thread.sleep(5000);

//Step 10:

            driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(PassportNumber);
            driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(Comment);
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),PassportNumber);
            Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),Comment);

            driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='Logout']")).click();
            Thread.sleep(2000);



            driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
            driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Auto@123");
            driver.findElement(By.xpath("//button[contains(string(),'Login')]")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//span[text()='My Info']")).click();
            Thread.sleep(2000);
    //Step17:
            Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstname);
            Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastname);
            Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),EmployeeID);

            driver.findElement(By.xpath("//a[text()='Immigration']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[text()='123-123-1234-12']/parent::div/following-sibling::div/div//i[@class='oxd-icon bi-pencil-fill']")).click();
            Thread.sleep(5000);

            Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),PassportNumber);
            Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),Comment);

        }
    @AfterClass
        public void afterClass(){

//        driver.quit();
        }
    }
