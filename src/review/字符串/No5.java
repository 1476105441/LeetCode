package review.字符串;

public class No5 {
    /**
     *      验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 提示：
     *     1 <= s.length <= 2 * 105
     *     字符串 s 由 ASCII 字符组成
     */

    char[] chars;
    public boolean isPalindrome(String s){
        chars = s.toCharArray();
        int left = 0 ,right = chars.length-1;

        while (left < right) {
            boolean flag1 = examine(left),flag2 = examine(right);
            if (!flag1 && !flag2) {
                left++;
                right--;
            } else if (!flag1) {
                left++;
            } else if (!flag2) {
                right--;
            }else{
                if (chars[left] > 64 && chars[left] < 91) {
                    chars[left] += 32;
                }
                if (chars[right] > 64 && chars[right] < 91) {
                    chars[right] += 32;
                }
                if (chars[right] != chars[left]) {
                    return false;
                }
                left++;
                right--;
            }
        }

        return true;
    }

    public boolean examine(int loc){
        return (chars[loc] > 64 && chars[loc] < 91) || (chars[loc] > 96 && chars[loc] < 123) || (chars[loc] > 47 && chars[loc] < 58);
    }
}
