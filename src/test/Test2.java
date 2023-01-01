package test;

public class Test2 {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(((Son)f).x);
    }

    //测试垃圾回收触发finalize
    /*static class myTest{
        @Override
        protected void finalize() throws Throwable {
            System.out.println("我被回收了，再见！");
        }
    }*/
}
class Father{
    int x = 10;

    public Father(){
        this.print();
        x = 20;
    }

    public void print() {
        System.out.println("Father.x = "+ x);
    }
}
class Son extends Father{
    int x = 30;
    public Son(){
        this.print();
        x = 40;
    }
    public void print(){
        System.out.println("Son.x = " + x);
    }
}