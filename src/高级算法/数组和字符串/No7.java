package 高级算法.数组和字符串;

import java.util.*;

public class No7 {
    /**
     *              最长连续序列
     *  给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */

    //想要使用并查集，但是失败了，因为我使用的是二级指针，然而二级指针不够用
    /*public int longestConsecutive(int[] nums){
        Map<Integer, Point> map = new HashMap<>();
        int max = 0,temp1,temp2,temp;

        for (int i = 0; i < nums.length; i++) {
            temp1 = nums[i] - 1;
            temp2 = nums[i] + 1;
            temp = 1;
            if (map.containsKey(nums[i])) {
                continue;
            }
            if (map.containsKey(temp1)) {
                Point p = map.get(temp1);
                p.memory.num++;
                temp = p.memory.num;
                map.put(nums[i],p);
            }
            if (map.containsKey(temp2)) {
                if (!map.containsKey(nums[i])) {
                    Point p = map.get(temp2);
                    p.memory.num++;
                    map.put(nums[i],p);
                    temp = p.memory.num;
                }else{
                    Point p1 = map.get(nums[i]);
                    Point p2 = map.get(temp2);
                    p1.memory.num += p2.memory.num;
                    p2.memory = p1.memory;
                    temp = p1.memory.num;
                }
            }
            if (!map.containsKey(nums[i])) {
                map.put(nums[i],new Point(new Num(1)));
            }
            if (temp > max) {
                max = temp;
            }
        }

        return max;
    }*/

    /**
     * 此题的最优解法：使用哈希表遍历
     */
    public int longestConsecutive(int[] nums){
        int n = nums.length, res = 0,x,length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        for (int num : set){
            if (set.contains(num - 1)) {
                continue;
            }
            length = 1;
            x = num + 1;
            while (set.contains(x)) {
                x++;
                length++;
            }
            if (length > res) {
                res = length;
            }
        }

        return res;
    }

    /**
     * 想法：效仿岛屿数量一题，使用根搜索式的并查集
     * 22ms
     */
    /*public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length, res = 1, temp1, temp2;
        int[] parent = new int[n];
        int[] rank = new int[n];
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            temp1 = nums[i] - 1;
            temp2 = nums[i] + 1;
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i],i);
            if (map.containsKey(temp1)) {
                int loc = map.get(temp1);
                parent[i] = find(parent, loc);
                rank[parent[i]] += 1;
                if (rank[parent[i]] > res) {
                    res = rank[parent[i]];
                }
            }
            if (map.containsKey(temp2)) {
                int loc1 = map.get(nums[i]),loc2 = map.get(temp2);
                int root1 = find(parent, loc1), root2 = find(parent, loc2);
                if (rank[root1] > rank[root2]) {
                    parent[root2] = root1;
                    rank[root1] += rank[root2];
                    if (rank[root1] > res) {
                        res = rank[root1];
                    }
                } else {
                    parent[root1] = root2;
                    rank[root2] += rank[root1];
                    if (rank[root2] > res) {
                        res = rank[root2];
                    }
                }
            }
        }

        return res;
    }

    public int find(int[] parent, int i) {
        if (parent[i] != i) {
            return find(parent, parent[i]);
        }
        return i;
    }*/
}
/*class Point{
    Num memory;

    public Point(Num memory) {
        this.memory = memory;
    }
}
class Num{
    public int num;

    public Num(int num) {
        this.num = num;
    }
}*/
