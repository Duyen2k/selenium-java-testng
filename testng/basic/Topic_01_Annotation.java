package basic;

import org.testng.annotations.*;

public class Topic_01_Annotation {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }

    @Test
    public void TC_01_(){
        System.out.println("Test Method 01");
    }

    @Test
    public void TC_02_(){
        System.out.println("Test Method 02");
    }

    @Test
    public void TC_03_(){
        System.out.println("Test Method 03");
    }
}
