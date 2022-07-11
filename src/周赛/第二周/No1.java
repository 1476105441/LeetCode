package 周赛.第二周;

public class No1 {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String s = scanner.next();

        String s1 = "hello";

        int loc = 0;
        char[] chars1 = s.toCharArray();
        char[] chars2 = s1.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[loc]) {
                loc++;
                if (loc == chars2.length) {
                    break;
                }
            }
        }

        if (loc == chars2.length) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}
