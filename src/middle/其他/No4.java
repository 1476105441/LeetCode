package middle.其他;

public class No4 {
    //              任务调度器
    //给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，
    // 并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
    //然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
    //你需要计算完成所有任务所需要的 最短时间 。

    //------------------------------------------------------------------------------
    //          暴力解法（或者说加上了一点点贪心的暴力解法？）
    //  程序耗时：663ms，慢出天际......
    /*public int leastInterval(char[] tasks, int n) {
        //使用两个map，一个存储任务数量，一个存储冷却时间
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        int res = 0,max,value,mv = 0;
        Character key,mk = ' ';

        for (int i = 0; i < tasks.length; i++) {
            if (!map1.containsKey(tasks[i])) {
                map1.put(tasks[i],1);
                map2.put(tasks[i],0);
            }else{
                map1.put(tasks[i],map1.get(tasks[i]) + 1);
            }
        }

        while (!map1.isEmpty()) {
            max = 0;
            Iterator<Map.Entry<Character,Integer>> it = map1.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Character, Integer> node = it.next();
                value = node.getValue();
                key = node.getKey();

                //找到任务数最大且冷却时间为0的任务来执行
                if (value > max && map2.get(key) == 0) {
                    max = value;
                    mk = key;
                    mv = value;
                }

            }

            //res代表执行时间
            res++;

            time(map2);
            //max为零，说明该时间并没有执行任务
            if (max == 0) {
                continue;
            }

            if (mv == 1) {
                //当前任务从两个集合中移除
                map1.remove(mk);
                map2.remove(mk);
            }else{
                map1.put(mk,mv-1);
                map2.put(mk,n);
            }
        }

        return res;
    }

    public static void time(Map<Character,Integer> map){
        Iterator<Map.Entry<Character,Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character,Integer> node = it.next();
            if (node.getValue() != 0) {
                map.put(node.getKey(),node.getValue()-1);
            }
        }
    }*/
    //----------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------
    //          根据公式计算
    /*public int leastInterval(char[] tasks, int n){
        Map<Character,Integer> map = new HashMap<>();
        int max = 0,temp,count = 0;

        for (int i = 0; i < tasks.length; i++) {
            temp = map.getOrDefault(tasks[i],0) + 1;
            map.put(tasks[i],temp);
            if (temp > max) {
                max = temp;
            }
        }

        Iterator<Map.Entry<Character,Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Integer> node = it.next();
            if (node.getValue() == max) {
                count++;
            }
        }

        temp = (max - 1) * (n + 1) + count;
        return temp > tasks.length ? temp : tasks.length;
    }*/
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    //          使用数组节省时间
    public int leastInterval(char[] tasks, int n){
        int[] nums = new int[26];
        int max = 0,count = 0,temp;

        for (int i = 0; i < tasks.length; i++) {
            nums[tasks[i] - 65]++;
            if (nums[tasks[i] - 65] > max) {
                max = nums[tasks[i] - 65];
            }
        }

        for (int i = 0; i < 26; i++) {
            if (nums[i] == max) {
                count++;
            }
        }

        temp = (max - 1) * (n + 1) + count;

        return temp > tasks.length ? temp : tasks.length;
    }
}
