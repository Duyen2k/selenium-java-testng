package JavaTester;

import java.util.Random;

public class Topic_03_Random {
    public static void main(String[] args){
        Random rand=new Random();
        System.out.println("donaltrump" + rand.nextBoolean()+ "@gmail.net");
        System.out.println("donaltrump" + rand.nextDouble()+ "@gmail.net");
        System.out.println("donaltrump" + rand.nextFloat() + "@gmail.net");
        System.out.println("donaltrump" + rand.nextInt() + "@gmail.net");
        System.out.println("donaltrump" + rand.nextLong() + "@gmail.net");

        System.out.println("donaltrump" + rand.nextInt(9999) + "@gmail.net");



    }
}
