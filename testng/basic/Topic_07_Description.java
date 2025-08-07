package basic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {
    @BeforeClass
    public void beforeclass(){

    }

    //Description: cung cấp thông tin thêm cho testcase
    //Liên kết với hệ thống quản lý document-design-testcase
    @Test(description = "Azure#9956 -Login to System and Verify user ìnformation")
    public void TC_01_register(){

    }

    @Test(groups="platformMobile",enabled = true,description = "Login to System via Admin/Normal Account")
    public void TC_02_login(){

    }
   //Set enable để ko chạy-skip
    @Test(enabled = false)
    public void TC_03_order(){

    }

    @Test
    public void TC_04_pay(){

    }

    @Test
    public void TC_05_ship(){

    }

    @AfterClass
    public void afterclass(){

    }
}
