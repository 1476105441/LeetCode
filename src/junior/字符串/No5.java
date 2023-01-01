package junior.字符串;

public class No5 {
    //                  验证回文串
    //  给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。要将特殊的字符去掉
    //
    //说明：本题中，我们将空字符串定义为有效的回文串。
    //
    //提示：
    //    1 <= s.length <= 2 * 105
    //    初级算法.字符串 s 由 ASCII 字符组成

    //-----------------------------------------------------------------------------------------
    //想法：首尾各一个指针进行双指针遍历(3ms，非最优解)
    /*public static boolean isPalindrome(String s) {
        int start = 0, n = s.length(), end = n - 1;
        //全转成小写的再比较，去空格
        char[] chars = s.toUpperCase().toCharArray();
        while (start < end) {
            while(start < n-1 &&(chars[start] < 48 || chars[start] > 90 || (chars[start] > 57 && chars[start] < 65))) {
                start++;
            }
            if (start >= end) {
                break;
            }
            while(end > 0 && (chars[end] < 48 || chars[end] > 90 || (chars[end] > 57 && chars[end] < 65))) {
                end--;
            }
            if (start >= end) {
                break;
            }
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }*/
    //-----------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------
    //大神写的双指针（用方法来过滤非字母和数字的，2ms）已是最优解：
    /*public boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            //因为题中说了，只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            //然后把两个字符变为小写，在判断是否一样，如果不一样，直接返回false
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }*/
    //-----------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------
    //自己想的能否优化大神的算法，结果没啥区别
    /*public boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            //因为题中说了，只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (left < right && !Character.isLetterOrDigit(chars[left]))
                left++;
            while (left < right && !Character.isLetterOrDigit(chars[right]))
                right--;
            //然后把两个字符变为小写，在判断是否一样，如果不一样，直接返回false
            if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right]))
                return false;
            left++;
            right--;
        }
        return true;
    }*/
    //-----------------------------------------------------------------------------------------
}
