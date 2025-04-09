package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

        @Test
        public void TC_01_Jquery() throws InterruptedException {
            driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        //span#salutation-button
        //ul#salutation-menu div
        //Dr,
            //chưa học
            //Wait explicit
            //Líst <WebElement> : nhieu element
            //Vòng lặp: for
            //Câu lệnh break
            //Viết hàm (reusable function): chọn đoạn code> extract> Method> đặt tên cho hàm
            //Tham số truyền vào (parameter)
        //Step1: Tìm dropdown
        //Step2:  Click vào dropdown
        //Step3: Xổ hết item
        //Step 4: Click vào item cần chọn
        //Dùng Assert để verify lại lựa chọn vừa đc select

            selectItemInDropdown("span#salutation-button","ul#salutation-menu div","Mrs.");

        }

    private void selectItemInDropdown(String parentLocator, String childLocator, String textItem) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(2000);
        //đợi xổ hết dữ liệu
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        //list ra tất cả các item bên trong và lưu vào một biê(kiểu dữ liệu là List)
        //Tips: Lấy locator pahir lấy đến cái thẻ chứa Text của item (vì mình đang getText)
        List<WebElement> Allitem = driver.findElements(By.cssSelector(childLocator));
        //Duyệt qua từng cái element để kiểm tra
        for (WebElement item: Allitem ){
            //Kiêm tra điều kiện: nếu text của item lấy ra bằng với mong đợi
            if (item.getText().equals(textItem)){
            item.click();
            break;
        }
        }
    }

    @Test
        public void TC_02_Speed() throws InterruptedException {
            driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
            selectItemInDropdown("span#speed-button","ul#speed-menu div","Fast");
        }

    @Test
    public void TC_03_Vue_dropdown() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

     driver.findElement(By.cssSelector("div.btn-group")).click();

     new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu li")));

     List <WebElement> Allitem=driver.findElements(By.cssSelector("ul.dropdown-menu li"));

     for (WebElement item:Allitem ){
         if(item.getText().trim().equals("Second Option")){
             item.click();
             Thread.sleep(2000);
             break;
         }
        }
    Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText().trim(),"Second Option");
    }

    @Test
    public void TC_04_Editable_Selectable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        driver.findElement(By.cssSelector("input.search")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input.search")).sendKeys("Algeria");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input.search")).clear();
        Thread.sleep(3000);
        //selectable
        driver.findElement(By.cssSelector("input.search")).click();
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.menu span")));
        List <WebElement> allitems_selectable = driver.findElements(By.cssSelector("div.menu span"));
        for (WebElement selected_item : allitems_selectable){
            if(selected_item.getText().trim().equals("Angola")){
                selected_item.click();
                Thread.sleep(2000);
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Angola");

    }
    @Test
    public void TC_05_Huawei() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        //Cặp data1
        HuaweiEditableSelectable("div[ht='input_emailregister_dropdown']>span","span.list-item-text","input[ht='input_emailregister_search']","Anguilla");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText().trim(),"Anguilla");
        //Cặp data2
        HuaweiEditableSelectable("div[ht='input_emailregister_dropdown']>span","span.list-item-text","input[ht='input_emailregister_search']","Angola");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown']>span")).getText().trim(),"Angola");

    }

    private void HuaweiEditableSelectable(String parentLocator,String ChildrenLocator,String SearchLocator, String SelectedText) throws InterruptedException {
        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentLocator)));
        driver.findElement(By.cssSelector(parentLocator)).click();
        //selectable
        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ChildrenLocator)));
        List <WebElement> Allcontries= driver.findElements(By.cssSelector(ChildrenLocator));
        for ( WebElement selected_contry: Allcontries){
            if(selected_contry.getText().trim().equals(SelectedText)){
                selected_contry.click();
                Thread.sleep(2000);
                break;
            }
        }
        //editable
        driver.findElement(By.cssSelector(parentLocator)).click();
        driver.findElement(By.cssSelector(SearchLocator)).sendKeys(SelectedText);
        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ChildrenLocator)));
        List <WebElement> Allcontries2= driver.findElements(By.cssSelector(ChildrenLocator));
        for ( WebElement selected_contry2: Allcontries2){
            if(selected_contry2.getText().trim().equals(SelectedText)){
                selected_contry2.click();
                Thread.sleep(2000);
                break;
            }
        }
    }


    @AfterClass
        public void afterClass(){
//            driver.quit();
        }
    }


