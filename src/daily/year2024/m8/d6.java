package daily.year2024.m8;

/**
 * @author wjs 2024/8/6
 */
public class d6 {
    //解法一：枚举法
    class Solution1 {
        //枚举法
        public String gcdOfStrings(String str1, String str2) {
            int n1 = str1.length(), n2 = str2.length();
            for(int i = Math.min(n1,n2);i > 0;i--) {
                //加上整除的判断，因为只有两个长度都整除了，才能构成该字符串
                if(n1 % i == 0 && n2 % i == 0) {
                    String t = str1.substring(0,i);
                    if(checkIfGcd(t,str1) && checkIfGcd(t,str2)) {
                        return t;
                    }
                }
            }
            return "";
        }
        private boolean checkIfGcd(String t, String s) {
            int len = s.length() / t.length();
            StringBuilder tmp = new StringBuilder();
            for(int i=0;i < len;i++) {
                tmp.append(t);
            }
            return tmp.toString().equals(s);
        }
    }

    //解法二：辗转相除法
    //推理出实际只需要判断一次就可以，也就是判断最大公约数长度的字符串是否为结果字符串
    class Solution2 {
        public String gcdOfStrings(String str1, String str2) {
            int n1 = str1.length(), n2 = str2.length();
            int x = gcd(n1,n2);
            String t = str1.substring(0,x);
            if(checkIfGcd(t,str1) && checkIfGcd(t,str2)) {
                return t;
            }
            return "";
        }

        private boolean checkIfGcd(String t, String s) {
            int len = s.length() / t.length();
            StringBuilder tmp = new StringBuilder();
            for(int i=0;i < len;i++) {
                tmp.append(t);
            }
            return tmp.toString().equals(s);
        }

        private int gcd(int x,int y) {
            while(y != 0) {
                int z = x % y;
                x = y;
                y = z;
            }
            return x;
        }
    }
}
