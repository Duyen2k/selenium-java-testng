package JavaTester;
public class Topic_02_Primitive_Reference {
    int x;

    public Topic_02_Primitive_Reference(int x) {
        x = x;

    }

    public Topic_02_Primitive_Reference() {

    }

    public static void main(String[] args) {

        int x = 37;
        int y = x;
        System.out.println("x=" + x);
        System.out.println("y=" + y);


        x=50;
        System.out.println("x=" + x);
        System.out.println("y=" + y);

        //class
        //Instance 1
        Topic_02_Primitive_Reference c = new Topic_02_Primitive_Reference();
        c.x=10;
        System.out.println("c.x="+ c.x);
        //Instance 2
        Topic_02_Primitive_Reference d = c;
        System.out.println("d.x="+d.x);

        c.x=100;
        System.out.println("c.x="+ c.x);
        System.out.println("d.x="+d.x);


    }
}