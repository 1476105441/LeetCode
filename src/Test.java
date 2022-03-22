import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        char[] chars = new char[10];
        for (int i = 0; i < 5; i++) {
            chars[i] = 'a';
        }

        String s = new String(chars);

        System.out.println(s);
    }
}