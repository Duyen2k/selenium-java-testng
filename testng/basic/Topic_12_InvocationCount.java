package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class Topic_12_InvocationCount {
    WebDriver driver;
    Random rand;
    String firstName, lastName, emailAddress, password, fullName;
    Properties props = new Properties();
    FileOutputStream outputStrem;
    String projectPath = System.getProperty("user.dir");

    @Parameters("browser")
    @BeforeClass
    public void initialBrowser(String browserName) throws FileNotFoundException {
        driver = new FirefoxDriver();
        rand = new Random();

        firstName = "Joe";
        lastName = "Biden";
        fullName = firstName + " " + lastName;
        password = "123456789";

        //Lưu đường dẫn để lưu data test
        String path = projectPath + "\\dataTest\\user.properties";
        //Mở luồng
        outputStrem = new FileOutputStream(path);

        switch (browserName.toUpperCase()){
            case "CHROME":
                driver=new ChromeDriver();
                break;
            case "FIREFOX":
                driver=new FirefoxDriver();
                break;
            case "EDGE":
                driver=new EdgeDriver();
                break;
            default:
                throw new RuntimeException("browser name is not support");
        }
    }

    @Test(invocationCount =1) //,timeOut = 10000) //Testcase được chạy lại 3 lần
    public void TC_01_Register() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/");

        emailAddress = "joebiden" + rand.nextInt(99999) + "@gmail.com";

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        // Tuyệt đối
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformationText = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        // Tương đối
        Assert.assertTrue(contactInformationText.contains(fullName) && contactInformationText.contains(emailAddress));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);

        // Logout
        driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();

        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        System.out.println("Email Address: " + emailAddress);
        System.out.println("Password: " + password);

        //Lưu vào thư mục user.properties
        props.setProperty("email", emailAddress);
        props.setProperty("password", password);
        props.store(outputStrem, null);
    }

    @AfterClass
    public void afterClass() throws IOException {
        //Đóng luồng
        outputStrem.flush();
        //Tắt trình duyệt
        driver.quit();
    }
}
