package 中级算法复习.其他;

public class No4 {
    //              任务调度器

    /**
     * 思考：解题的关键是什么？  寻找规律
     * 题目的最优解与哪些条件有关？ 数量最多的元素数量、每个任务之间的冷却时间
     * 思路：只要让每个任务之间尽量没有空隙，就能求得最优解
     */
    /*public int leastInterval(char[] tasks,int n){
        Map<Character,Integer> map = new HashMap<>();
        int max = 0,count = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (map.containsKey(tasks[i])) {
                int value = map.get(tasks[i]);
                map.put(tasks[i],value+1);
                if (max < value+1) {
                    max = value+1;
                }
            }else{
                map.put(tasks[i],1);
            }
        }

        for(Map.Entry<Character,Integer> node : map.entrySet()){
            if (node.getValue() == max) {
                count++;
            }
        }

        int res = (max-1) * (n + 1) + count;

        if (res < tasks.length) {
            return tasks.length;
        }else {
            return res;
        }
    }*/

    //使用数组代替哈希表，因为总共只有26个英文字母
    public int leastInterval(char[] tasks,int n){
        int[] nums = new int[26];
        int max = 0,count = 0,res;

        for (int i = 0; i < tasks.length; i++) {
            int loc = tasks[i] - 'A';
            nums[loc]++;
            if (max < nums[loc]) {
                max = nums[loc];
            }
        }

        for (int i = 0; i < 26; i++) {
            if (nums[i] == max) {
                count++;
            }
        }

        res = (max - 1) * (n + 1) + count;

        if (res < tasks.length) {
            return tasks.length;
        }else{
            return res;
        }
    }
}
