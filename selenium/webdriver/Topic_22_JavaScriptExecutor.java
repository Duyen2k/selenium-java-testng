package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_22_JavaScriptExecutor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String inputemail;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    //KHởi tạo (ép kiểu tường minh)
        jsExecutor= (JavascriptExecutor) driver;

        inputemail= "DolnanTrump"+ new Random().nextInt(999)+"@gmail.com";
    }

        @Test
        public Object executeForBrowser(String javaScript) {
            return jsExecutor.executeScript(javaScript);
        }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }


    @Test
    public void TC_01_() throws InterruptedException {
        navigateToUrlByJS("https://live.techpanda.org/");
        //Get domain của page
//        String domain= (String) jsExecutor.executeScript("return document.domain");
//        Assert.assertEquals(domain,"live.techpanda.org");
        //Có thể sử dụng hàm đã định nghĩa sẵn(refactor) như ở trên để sử dụng nhanh hơn
        String domainTechPanda= (String) executeForBrowser("return document.domain");
        Assert.assertEquals(domainTechPanda,"live.techpanda.org");

        //Get URL của page
//        String urlpage= (String) jsExecutor.executeScript("return document.URL");
        String urlpage= (String) executeForBrowser("return document.URL");
        Assert.assertEquals(urlpage,"https://live.techpanda.org/");
        //Open MOBILE page sử dụng JE
        clickToElementByJS("//a[text()='Mobile']");
        Thread.sleep(2000);

        //Add Samsung Galaxy vào Cart
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        Thread.sleep(2000);

        //Verify success message
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
        //Cách 2 để verify
        String innerText= (String) executeForBrowser("return document.documentElement.innerText");
        Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
        //Cách 3 để verify
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Samsung Galaxy was added to your shopping cart.");


        //Mở Customer Service:
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(2000);
        //Scroll tới Newletter textbox
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("input[name='email']")));
        //Input email hợp lệ vào Newletter textbox
        sendkeyToElementByJS("//input[@name='email']",inputemail);
        Thread.sleep(2000);

        //Click vào Subcribe
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[text()='Subscribe']")));
        Thread.sleep(5000);

        Alert alert= new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        //Verify text
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Thank you for your subscription.");

       //Navigate tới domain
        navigateToUrlByJS("https://www.facebook.com/");
        String facebookURL= (String) executeForBrowser("return document.URL");
        System.out.println(facebookURL);

        //Lưu ý: JS ko dùng được với wait để đợi mà sleep cứng
    }

    @Test
    public void TC_02_Disable_element() throws InterruptedException {
        navigateToUrlByJS("https://live.techpanda.org/index.php/");
        //click vào element bị ẩn
        clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");

        //Click vào 1 element mà ko cần Hover chuột vào Menu/Tooltip
        navigateToUrlByJS("https://www.fahasa.com/");
        Thread.sleep(2000);
        By popup=By.cssSelector("div.brz-container");

        if(driver.findElement(popup).isDisplayed()){
            driver.findElement(By.cssSelector("div.brz-popup2__close")).click();
        }

        clickToElementByJS("//span[text()='Đồ Chơi']");

    } //Chỉ nên dùng JS khi ko thể dùng Selenium

    @Test
    public void TC_03_HTML5() throws InterruptedException {
        driver.get("https://account.rode.com/register");
        //Click vào Register button
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        Thread.sleep(2000);
        //Verify text lỗi
        WebElement validateEmail=driver.findElement(By.cssSelector("input[id='name']"));
        Assert.assertEquals(validateEmail.getDomProperty("validationMessage"),"Please fill out this field.");

        //Send key vào email
        driver.findElement(By.cssSelector("input[id='name']")).sendKeys("automation test");
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        Thread.sleep(2000);
//        Assert.assertEquals();

    }
    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }
