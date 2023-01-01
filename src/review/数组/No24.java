package review.数组;

import java.util.HashMap;
import java.util.Map;

public class No24 {
    /**
     *      最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * 提示：
     *     0 <= nums.length <= 105
     *     -109 <= nums[i] <= 109
     */

    //第一种解法：并查集
    /*public int longestConsecutive(int[] nums){
        Map<Integer,Node> map = new HashMap<>();
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if(map.get(nums[i]) == null){
                Node node = new Node();
                node.count = 1;
                map.put(nums[i],node);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            Node node = map.get(nums[i]),node1,node2;
            int temp = 1;
            if (node.next == null) {
                node2 = map.get(nums[i]-1);
                if (node2 != null && node2.next == null) {
                    node2.next = node;
                    node.count += node2.count;
                    temp = node.count;
                }
                node1 = map.get(nums[i]+1);
                if (node1 != null) {
                    node.next = node1;
                    node1 = getRoot(node1);
                    node1.count += node.count;
                    temp = node1.count;
                }

                if (temp > max) {
                    max = temp;
                }
            }
        }

        return max;
    }*/

    public Node getRoot(Node node){
        if (node.next != null) {
            return getRoot(node.next);
        }
        return node;
    }

    //第二种解法，使用哈希表存储每个元素，遍历哈希表中的元
    //素，从每个没有前驱的元素开始往后遍历统计个数，最大的即为要找的值
    /*public int longestConsecutive(int[] nums){
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (Integer i : set){
            if (!set.contains(i - 1)) {
                int j = i+1,temp = 1;
                while (set.contains(j)) {
                    j++;
                    temp++;
                }
                if (max < temp) {
                    max = temp;
                }
            }
        }

        return max;
    }*/


    //另一种并查集解法
    int[] parent,count;
    public int longestConsecutive(int[] nums){
        parent = new int[nums.length];
        count = new int[nums.length];
        Map<Integer,Integer> map = new HashMap<>();
        int p1,p2,max,res = 0;
        Integer small,big;

        for (int i = 0; i < nums.length; i++) {
            parent[i] = i;
            count[i] = 1;
            if (map.get(nums[i]) == null) {
                map.put(nums[i],i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            p1 = parent[i];
            max = 1;
            if (p1 == map.get(nums[i])) {
                small = map.get(nums[i]-1);
                if (small != null && parent[small] == small) {
                    count[p1] += count[parent[small]];
                    parent[parent[small]] = p1;
                    max = count[p1];
                }

                big = map.get(nums[i]+1);
                if (big != null && parent[big] == big) {
                    p2 = findParent(big);
                    count[p2] += count[p1];
                    parent[p1] = p2;
                    max = count[p2];
                }
            }
            if (max > res) {
                res = max;
            }
        }

        return res;
    }

    public int findParent(int i){
        if (parent[i] != i) {
            return findParent(parent[i]);
        }
        return i;
    }
}

class Node{
    int count;
    Node next;
}
