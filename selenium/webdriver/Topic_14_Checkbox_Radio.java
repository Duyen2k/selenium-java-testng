package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_14_Checkbox_Radio {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //Một số lý do khiến chạy testcase bị fail
    //1. Do browser mở ra nhung ko maximize
    //2. Browser maximize nhưng icon loading chưa biến mất
    //3. Browser co maximize,icon loading đã mất NHƯNG màn hình nhỏ

    //Khắc phục:
    //- Maximize browser lên,set độ phân dải màn hình lên cao nhất
    //- Thêm Thread.sleep() để đợi icon loading load xong

    @Test
    public void TC_01_KendoUI_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#demo-runner")));

        By DualzoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        //Verify whether checkbox is selected
        if (!driver.findElement(DualzoneCheckbox).isSelected()) {
            driver.findElement(DualzoneCheckbox).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(DualzoneCheckbox).isSelected());

        //Verify whether checkbox is deselected
        if (driver.findElement(DualzoneCheckbox).isSelected()) {
            driver.findElement(DualzoneCheckbox).click();
            Thread.sleep(2000);
        }
        Assert.assertFalse(driver.findElement(DualzoneCheckbox).isSelected());

        //Radiobutton
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(2000);
        //scroll to target element area
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#demo-runner")));
        By TargetRadiobutton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        if (!driver.findElement(TargetRadiobutton).isSelected()) {
            driver.findElement(TargetRadiobutton).click();
        }

    } //label[text()='Pick your favorite season']/preceding-sibling::div

    @Test
    public void TC_02_Radio_button() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[text()='Pick your favorite season']")));
        By SummerRadio = By.xpath("//label[text()='Summer']/preceding-sibling::div");
        if (!driver.findElement(SummerRadio).isSelected()) {
            driver.findElement(SummerRadio).click();
        }
        driver.get("https://material.angular.io/components/checkbox/examples");
        By Checked_checkbox = By.xpath("//label[text()='Checked']/parent::div//input");
        By Interminate_checkbox = By.xpath("//label[text()='Indeterminate']/parent::div//input");
        //Kiểm tra được chọn chưa, nếu chưa thì chọn
        if (!driver.findElement(Checked_checkbox).isSelected()) {
            driver.findElement(Checked_checkbox).click();
        }
        if (!driver.findElement(Interminate_checkbox).isSelected()) {
            driver.findElement(Interminate_checkbox).click();
        }
        Assert.assertTrue(driver.findElement(Checked_checkbox).isSelected());
        Assert.assertTrue(driver.findElement(Interminate_checkbox).isSelected());

        //Kiểm tra được chọn chưa, nếu rồi thì bỏ chọn
        if (driver.findElement(Checked_checkbox).isSelected()) {
            driver.findElement(Checked_checkbox).click();
            Thread.sleep(2000);
        }
        if (driver.findElement(Interminate_checkbox).isSelected()) {
            driver.findElement(Interminate_checkbox).click();
            Thread.sleep(2000);
        }
        Assert.assertFalse(driver.findElement(Checked_checkbox).isSelected());
        Assert.assertFalse(driver.findElement(Interminate_checkbox).isSelected());

    }

    @Test
    public void TC_03_CheckboxMultiField() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div.form-single-column input[type='checkbox']")));
        //Chọn tất cả checkbox
        List<WebElement> allcheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement checkbox : allcheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected());
        }
    //Chỉ chọn 1 checkbox
        for (WebElement checkbox : allcheckboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertFalse(checkbox.isSelected());
        }
        //Cách 1: cách thủ công nhưng nếu muốn đổi lấy giá trị khác thì ko tối ưu
        By HeartAttackElement =By.xpath("//label[contains(text(),'Heart Attack')]/parent::span/input");
        driver.findElement(HeartAttackElement).click();
        Assert.assertTrue(driver.findElement(HeartAttackElement).isSelected());
        //Cách 2: getAttribute và so sánh với giá trị cần chọn để dễ thay đổi value
        for(WebElement Onecheckbox: allcheckboxes){
            if(Onecheckbox.getDomAttribute("value").equals("Rheumatic Fever") && !Onecheckbox.isSelected()){
                Onecheckbox.click();
            }
        }
        for(WebElement Onecheckbox:allcheckboxes){
            if (Onecheckbox.getDomAttribute("value").equals("Rheumatic Fever")) {
                Assert.assertTrue(Onecheckbox.isSelected());
            }
        }
    }


    @AfterClass
    public void afterClass() {
//            driver.quit();
    }

}