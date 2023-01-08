package contest.year2023.m1.d1;

public class No1 {
    public int countDigits(int num) {
        int[] set = new int[10];
        int val = num,res = 0;
        while (val > 0) {
            set[val % 10]++;
            val /= 10;
        }
        for (int i = 0; i < 10; i++) {
            if(num % i == 0)
                res += set[i];
        }
        return res;
    }
}
