package contest.year2023.m3.d19;

public class No4 {
    /*public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        Arrays.sort(nums);
        Map<Integer,Integer> map = new HashMap<>();
        int lose = 0;
        for(int i = 0;i < n;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        Integer v1 = null,v2 = null;
        while((v1 = map.get(lose-value)) != null || (v2 = map.get(lose+value)) != null){
            boolean flag = false;
            if(v1 != null){
                if(v1 == 1 && lose-value < 0){
                    map.remove(lose-value);
                    flag = true;
                }
                else if(v1 > 1) {
                    map.put(lose-value,v1-1);
                    flag = true;
                }
            }
            if(v2 != null) {
                if(v2 == 1){
                    map.remove(lose+value);
                    flag = true;
                }
                else{
                    map.put(lose+value,v1-1);
                    flag = true;
                }
            }
            if(!flag) break;
            lose++;
        }
        return lose;
    }*/


    /*public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        int[] set = new int[value+1];
        for(int i=0;i < n;i++){
            int loc = nums[i] % value + value;
            set[loc%value]++;
        }
        //检查总共出现了多少组
        int min = 1000000001;
        for(int i=0;i < value;i++){
            if(set[i] < min) min = set[i];
        }
        int res = min * value;
        for(int i=0;i < value;i++){
            if(set[i]-min <= 0) break;
            res++;
        }
        return res;
    }*/

    //简单写法
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        int[] set = new int[value+1];
        for(int i=0;i < n;i++){
            int loc = nums[i] % value + value;
            set[loc%value]++;
        }
        int res = 0;
        while(set[res % value] > 0){
            set[res%value]--;
            res++;
        }
        return res;
    }
}
