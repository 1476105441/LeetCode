package contest.year2022.m10.m10d16;

public class No3 {
    public boolean sumOfNumberAndReverse(int num) {
        if(num == 0)
            return true;
        for (int i = 1; i < num; i++) {
            int x = reverse(i);
            if (i + x == num) {
                System.out.println(i);
                System.out.println(x);
                System.out.println(num);
                return true;
            }
        }
        return false;
    }
    public int reverse(int num){
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
            res *= 10;
        }
        return res /= 10;
    }

}
