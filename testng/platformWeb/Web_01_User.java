package platformWeb;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Web_01_User {
    @BeforeClass
    public void beforeClass(){

    }

    @Test
    public void login(){
        //Bắt buộc l kiểu dữ liệu boolean
        //Selenium: tiền tố là isXXX: Displayed/isEnabled/isSelected/isMultiple
        //Hàm tự define trả về blean
        //True: cần kiểm tra dữ liệu trả về là đúng
        //False: khi cần kiểm tra dữ liệu trả về là sai
        Assert.assertTrue(5>3);

        boolean status=5<3;
        System.out.println(status);
        Assert.assertFalse(status);

        //Equals: kiểm tra dữ liệu mong đợi vs thực tế giống nhau
        //2 vế đều cùng một kiểu dữ liệu
        //Kiểm tra về giá trị của kiểu dữ liệu và dữ liệu
        String studentName="Đặng Thùy Trâm";
        Assert.assertEquals(studentName,"Đặng Thùy Trâm");

        Object name="Đặng Thùy Trâm";
        Assert.assertEquals(studentName,name);

        int number=15;
        float fnumber=15;
        Assert.assertEquals(number,fnumber); //Thằng int hoặc float bị ép kiểu sang, vì int nằm trong dải float





    }

    @AfterClass
    public void afterclass(){

    }
}
