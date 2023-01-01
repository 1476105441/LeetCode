package junior.数组;

public class No7 {
    //                    加一
    //  给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
    //  最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
    //  你可以假设除了整数 0 之外，这个整数不会以零开头。
    //
    //提示：
    //    1 <= digits.length <= 100
    //    0 <= digits[i] <= 9

    //击败百分之百
    public int[] plusOne(int[] digits) {
        //使用point遍历数组
        int point = digits.length - 1;
        for (; point > -1; point--) {
            digits[point] += 1;
            //等于10的话就进一位
            if (digits[point] == 10) {
                digits[point] = 0;
                //如果point指向数组首位的元素，就给数组增加一位
                if (point == 0) {
                    int[] res = new int[digits.length+1];
                    res[0] = 1;
                    for(int i=1;i<res.length;i++){
                        res[i] = digits[i-1];
                    }
                    return res;
                }
            }else{
                break;
            }
        }
        return digits;
    }

}
