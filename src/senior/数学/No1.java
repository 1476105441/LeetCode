package senior.数学;

import java.util.Arrays;

public class No1 {
    /**
     *      最大数
     */

    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();

        quickSort(nums,0,nums.length-1);
        if(nums[0] == 0)
            return "0";
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }

        return sb.toString();
    }

    public static int compare(int x,int y){
        int t1 = 10,t2 = 10;
        while (t1 <= x) {
            t1 *= 10;
        }
        while (t2 <= y) {
            t2 *= 10;
        }
        return (x*t2)+y-x-(y*t1);
    }
    public static void quickSort(int[] nums,int l,int r){
        if (l > r) {
            return;
        }
        int i = l,j = r;
        while (i < j) {
            while (i < j && compare(nums[i], nums[j]) >= 0) {
                j--;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && compare(nums[i], nums[j]) > 0) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
        quickSort(nums,l,i-1);
        quickSort(nums,i+1,r);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{23,22,21,53,7,8,9,22,22};
        quickSort(nums,0,nums.length-1);
        Arrays.stream(nums).forEach(System.out::println);
    }

    //自己的想法太过于复杂
    /*public void quickSort(int[] nums){

    }
    public int compare(int x,int y){
        int n = (int) Math.max(Math.log10(x)+1,Math.log10(y)+1);
        for (int i = 1; i <= n; i++) {
            int t1 = get(x,i),t2 = get(y,i);
            if (t1 == -1) {
                if (t2 == -1) {
                    return 0;
                }
                t1 = get(x,i-1);
                if (t1 > t2) {
                    return 1;
                } else if(t1 < t2){
                    return -1;
                }
            } else if (t2 == -1) {

            } else if (t1 > t2){
                return 1;
            } else if (t1 < t2) {
                return -1;
            }
        }
        return 1;
    }
    public static int get(int x,int n){
        if (x < Math.pow(10, n - 1)) {
            return -1;
        }
        int tmp = x,c = 1,y = 100000000;
        while (y > 0) {
            if (tmp > y) {
                if (c == n) {
                    tmp = tmp / y;
                    break;
                }
                tmp = tmp%y;
                c++;
            }
            y /= 10;
        }
        return tmp;
    }*/
}
