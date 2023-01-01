package middle.数组和字符串;

public class No5 {
    //                      最长回文子串
    //给你一个字符串 s，找到 s 中最长的回文子串。

    //-------------------------------------------------------------------
    //                      50ms（非最优解）
    //动态规划：如果一个字符串是回文串那么他的子串也一定是回文串
    //二维数组dp[i][j]表示第i个元素为起始位置，长度为j的字符串是否是回文串
    /*public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        int max = 0;

        boolean[][] bp = new boolean[chars.length][chars.length];

        int l,i,j,start = 0,end = 1;

        //处理边界
        for (i = 0; i < chars.length;i++) {
            bp[i][i] = true;
        }

        for (l = 2;l <= chars.length;l++){
            for (i = 0;i + l - 1 < chars.length;i++){
                j = i + l - 1;
                if (chars[i] == chars[j]) {
                    if (l == 2 || bp[i + 1][j - 1]) {
                        bp[i][j] = true;
                        if (l > max) {
                            max = l;
                            start = i;
                            end = j + 1;
                        }
                    } else{
                        bp[i][j] = false;
                    }
                }else{
                    bp[i][j] = false;
                }
            }
        }

        return s.substring(start,end);
    }*/
    //---------------------------------------------------------------------------


    //---------------------------------------------------------------------------
    //                      中心扩散法：此题的更优解
    //我自己写的版本：8ms，击败百分之95
    //从回文中心（字符串长度为1或相等的2个字符）开始往外扩散，两个端点的字符相等
    // 则继续扩散，不等则停止扩散
    /*public String longestPalindrome(String s){
       char[] chars = s.toCharArray();

       int start = 0,end = 1,length,max = 0,j,k;

        for (int i = 0; i < chars.length ; i++) {
            j = i - 1;
            k = i + 1;
            length = 1;



            for (;j > -1 && k < chars.length ;j--,k++){
                if (chars[j] != chars[k]) {
                    break;
                }
                length += 2;

            }

            if (length > max) {
                max = length;
                start = j + 1;
                end = k;
            }

            //是否要从2开始扩散
            if (i + 1 < chars.length && chars[i] == chars[i + 1]) {
                length = 2;
                k = i + 2;
                j = i - 1;
                for (;j > -1 && k < chars.length ;j--,k++){
                    if (chars[j] != chars[k]) {
                        break;
                    }
                    length += 2;
                }
            }

            if (length > max) {
                max = length;
                start = j + 1;
                end = k;
            }

        }

       return s.substring(start,end);
    }*/
    //---------------------------------------------------------------------------

    //---------------------------------------------------------------------------
    //                              马拉车算法
    //将字符串每两个字符之间进行填充特殊字符，这样每个回文串都会被填充成为奇数个数，
    // 再进行计算回文半径，计算时有三种情况，
    // 1、若是当前元素i在回文半径右边端点在最右边元素maxCenter的右半径内且与之对称
    // 的在左半径内的元素j的回文半径仍在最大回文半径内，则当前元素不需要再额外计算，
    // 直接等于j的回文半径
    // 2、若是当前元素i在回文半径右边端点在最右边元素maxCenter的右半径内，但与之对
    // 称的在左半径内的元素j的回文半径不在最大回文半径范围内，则当前元素的回文半径从
    // maxRight-i开始往外扩张（回文半径不是maxRight-i+1吗？）
    // 3、若是当前元素不在最大回文半径元素maxCenter的右半径内，则从头开始计算
    public String longestPalindrome(String s){
        char[] chars = s.toCharArray();

        //增添了特殊字符的字符数组
        int length = chars.length * 2 + 1;
        char[] temp = new char[length];
        //计算每个元素回文半径的数组
        int[] value = new int[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                temp[i] = '#';
            }else{
                temp[i] = chars[index++];
            }
        }

        //maxCenter存放的是回文半径右边端点在最右边的元素！！并不是
        // 回文半径最大的元素，resCenter才是，maxRight是最右边端点
        // 的坐标，max是最大回文半径
        int maxCenter = 0,maxRight = 0,max = 0,resCenter = 0;

        for (int i = 0; i < length; i++) {
            //此处判断必须是i在最右边回文半径内部！！！
            //就是因为这个小细节，卡了我半天
            if (i < maxRight) {
                if (value[2 * maxCenter - i] < maxRight - i + 1) {
                    //第一种情况
                    value[i] = value[2 * maxCenter - i];
                }else{
                    //第二种情况
                    value[i] = maxRight - i + 1;
                    while (i - value[i] > -1 && i + value[i] < length && temp[i - value[i]] == temp[i + value[i]]) {
                        value[i]++;
                    }
                }
            }else{
                value[i] = 1;
                while (i - value[i] > -1 && i + value[i] < length && temp[i - value[i]] == temp[i + value[i]]) {
                    value[i]++;
                }
            }

            //更新maxCenter
            //此处也是重点：maxCenter和resCenter不是同一个！！maxCenter是右半径在最右边的元素
            //而resCenter是回文半径最长的元素！！
            if (i + value[i] - 1 > maxRight ) {
                maxCenter = i;
                maxRight = i + value[i] - 1;
            }

            if (max < value[i]) {
                max = value[i];
                resCenter = i;
            }
        }

        max--;
        int start = (resCenter - max) >> 1;
        return s.substring(start,start + max);
    }
}
