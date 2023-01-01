package review.数组;

public class No7 {
    /**
     *      加一
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */

    public int[] plusOne(int[] digits){
        int c = 0;
        if (digits[digits.length - 1] == 9) {
            c = 1;
            digits[digits.length-1] = 0;
            if (digits.length == 1) {
                return change(digits);
            }
        }else{
            digits[digits.length-1]++;
            return digits;
        }

        for (int i = digits.length-2; i >= 0; i--) {
            if (c == 1) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                }else{
                    digits[i]++;
                    c = 0;
                }
            }
        }

        if (c == 1) {
            return change(digits);
        }
        return digits;
    }

    //增加一位数字的方法
    public int[] change(int[] nums){
        int[] temp = new int[nums.length+1];
        temp[0] = 1;

        for (int i = 1; i < temp.length; i++) {
            temp[i] = nums[i-1];
        }

        return temp;
    }
}
