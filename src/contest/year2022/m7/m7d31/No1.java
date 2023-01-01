package contest.year2022.m7.m7d31;

public class No1 {
    /**
     *      使数组中所有元素都等于零
     */

    //要使数组中的元素全为0，则最大的元素要减为0
    public int minimumOperations(int[] nums) {
        int[] bucket = new int[101];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        int res = 0,c = 0;

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                int temp = i - c;
                while(temp > 0){
                    c += temp;
                    temp -= c;
                    res++;
                }
            }
        }

        return res;
    }
}
