package test;


public class Demo {
    private static final Integer num2 = 2;

    private int num = 1;

    public int add(){
        num = num + 2;
        return num;
    }

    public void switch1(int num) throws RuntimeException{
        int temp;
        switch (num) {
            case 101:
                temp = 1;
                break;
            case 102:
                temp = 2;
                break;
            case 103:
                temp = 3;
                break;
            default:
                temp = 4;
        }
        try {
            int m = 1,n = 0;
            System.out.println(m/n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void test(){
        int i = 0;
        try{
            i = 100 / 0;
        }finally {
            i = 50;
        }
        System.out.println(i);
    }

    public static void main(String[] args) {
        test();
    }
}
