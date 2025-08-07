package basic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Priority {
    @BeforeClass
    public void beforeclass(){

    }

    //Cách 1: đánh priority
    //Cách 2: Đánh trực tiếp vào tên TC(vì nó chạy ưu tiên số ASC-sau đó đến chữ alphabet)
    @Test(priority = 1)
    public void TC_01_register(){

    }

    @Test
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
