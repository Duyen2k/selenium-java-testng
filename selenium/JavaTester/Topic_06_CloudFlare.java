package JavaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeClass;

public class Topic_06_CloudFlare {
    WebDriver driver;
    @BeforeClass
    public void beforeclass(){
        //Add profile để chạy những page chặn auto
        //edge://version
        EdgeOptions edgeOptions =new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:C:C:\\Users\\ADMIN\\AppData\\Local\\Microsoft\\Edge\\User Data\\");
        edgeOptions.addArguments("--profile-directory=Default");
        driver=new EdgeDriver();

        //Chạy lại testcase
        //Mở ra màn hình capcha-> manual check vào capcha-> tắt
        //Chạy lại testcase

    }
}
