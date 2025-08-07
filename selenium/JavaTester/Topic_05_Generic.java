package JavaTester;

import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;

public class Topic_05_Generic {
    public static void main(String[] args) {
    //Non-Generic
        ArrayList studentName=new ArrayList();
        studentName.add("Nguyen Nhu Thuy");
        studentName.add(25);
        studentName.add(25.6);
        studentName.add(true);
        //Ko bị báo lỗi vì đây là kieểu non-generic(ko define nó là kiểu dữ liệu gì)

    //Generic
        ArrayList<String> students=new ArrayList<String>();
        students.add("Nguyen Nhu Thuy");
        //students.add(25); //add như này sai, vì String là kiểu dữ liệu-> phải đưa giá trị úng kiểu String

    }

    //setTimeout(() => {debugger;}, 3000);  (debug)
}
