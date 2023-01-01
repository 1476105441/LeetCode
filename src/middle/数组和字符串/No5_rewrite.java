package middle.数组和字符串;

public class No5_rewrite {
    //          最长回文子串重写
    //3种方法：1、动态规划   2、中心扩散   3、马拉车算法
    //-------------------------------------------------------------
    //1、动态规划
    //首先问个问题：为什么要使用动态规划？题目要求找最长回文子串，属于最优解问题
    //最长回文子串的子串也是回文串
    //如何找到动态规划转移方程？
    // 如起始下标为i的长度为l的子串，如何判断其是否是回文子串？
    // 只需要检查首尾字符是否相等，若是相等，则看去掉首尾两个字符之后的子串是否是回文串
    // 还要判断当前的字符串长度是否为2，若是为2，则直接说明是回文串
    /*public String longestPalindrome(String s){
        char[] chars = s.toCharArray();

        int n = chars.length,max = 0,begin = 0,end = 0;
        //dp[i][j]代表从i到j的字符串是否是回文串
        boolean[][] dp = new boolean[n][n];

        //初始化子问题，当字符串长度为1时，必定为回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        //解决后续子问题，两个变量：字符串的长度和字符串的起始下标，有了起始下标就可以确定结束下标
        for (int l = 2; l <= n; l++) {
            //i是起始下标
            for (int i = 0; i + l - 1 < n; i++) {
                //j是结束下标
                int j = i + l - 1;

                //首尾相等则判断去掉首尾的子串是否是回文串
                if (chars[i] == chars[j]) {
                    if (l == 2) {
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    //记录最大长度
                    if (dp[i][j] && l > max) {
                        max = l;
                        begin = i;
                        end = j;
                    }
                }else{
                    dp[i][j] = false;
                }
            }
        }

        return s.substring(begin,end+1);
    }*/

    //------------------------------------------------------------------------------
    //2、中心扩散法
    //以1个元素或者两个相同元素为中心向外扩散
    /*public String longestPalindrome(String s){
        char[] chars = s.toCharArray();

        int max = 0,begin = 0,end = 0,n = chars.length,length;

        for (int i = 0; i < n; i++) {
            length = 1;
            //以当前元素为中心向外扩散
            int j = i - 1,k = i + 1;
            while (j > -1 && k < n) {
                if (chars[j] == chars[k]) {
                    length += 2;
                }else{
                    break;
                }
                if (length > max) {
                    max = length;
                    begin = j;
                    end = k;
                }
                j--;
                k++;
            }

            //若当前元素和下一个元素的值相同，则以这两个元素为中心向外扩散
            int l = i + 1;
            if (l < n && chars[l] == chars[i]) {
                length = 2;
                j = i - 1;
                k = l + 1;
                while (j > -1 && k < n) {
                    if (chars[j] == chars[k]) {
                        length += 2;
                    }else{
                        break;
                    }
                    j--;
                    k++;
                }
                if (length > max) {
                    max = length;
                    begin = j + 1;
                    end = k - 1;
                }
            }
        }

        return s.substring(begin,end + 1);
    }*/

    //---------------------------------------------------------------------
    //3、重头戏：马拉车算法
    //思想：回文串的长度可能是奇数也可能是偶数，为了有同样的处理方式，马拉车算法采用填充的
    // 方式将回文串全部填充为奇数的形式来处理
    //寻找回文半径
    public String longestPalindrome(String s){
        //1、将元字符串填充为各回文子串都是奇数长度的字符串
        char[] src = s.toCharArray(),chars;
        //记录最长回文子串的中心center和半径radius，回文半径包含范围最右边的回文中心
        // rightCenter和其最右边的端点right
        int n = src.length,center = 0,radius = 0,rightCenter = 0,rightRadius = 0,m = 2 * n + 1;
        //存放回文半径的数组
        int[] length = new int[m];

        chars = new char[m];
        for (int i = 0; i < m; i++) {
            if ((i & 1) == 1) {
                chars[i] = src[i/2];
            }else{
                chars[i] = '*';
            }
        }

        for (int i = 0; i < m; i++) {
            //在最右边的回文子串的半径内
            if (i < rightCenter + rightRadius) {
                //找到i对应的左半边的端点j
                int j = 2 * rightCenter - i;
                if (j - length[j] > rightCenter - rightRadius) {
                    length[i] = length[j];
                }else{
                    //进行中心扩散
                    //将i的回文半径设置为从最右回文半径到i的距离，再进行中心扩散
                    length[i] = rightCenter + rightRadius - i;
                    int l = i - length[i] - 1,r = i + length[i] + 1;
                    while (l > -1 && r < m) {
                        if (chars[l] != chars[r]) {
                            break;
                        }
                        length[i]++;
                        l--;
                        r++;
                    }
                }
            }else{
                length[i] = 0;
                int l = i - length[i] - 1,r = i + length[i] + 1;
                while (l > -1 && r < m) {
                    if (chars[l] != chars[r]) {
                        break;
                    }
                    length[i]++;
                    l--;
                    r++;
                }
            }
            //更新最右回文中心和半径
            if (i + length[i] > rightCenter + rightRadius) {
                rightCenter = i;
                rightRadius = length[i];
            }
            //更新最长回文子串的中心和半径
            if (length[i] > radius) {
                center = i;
                radius = length[i];
            }
        }

        //计算完了之后，从填充字符串转换到原字符串
        int begin = (center - radius) / 2,end = (center + radius) / 2;

        return s.substring(begin,end);
    }

    public static void main(String[] args) {
        No5_rewrite no5 = new No5_rewrite();

        System.out.println(no5.longestPalindrome("aaa"));
    }
}
