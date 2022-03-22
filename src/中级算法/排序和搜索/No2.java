package 中级算法.排序和搜索;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class No2 {
    //                  前 K 个高频元素
    //给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

    //-----------------------------------------------------------------------------------------
    //想法：先遍历一遍数组，统计每个元素出现的次数，使用hash表存储
    //快排虽然可以是最快的解法之一，但是实际上并不是满足题目要求的解法
    /*public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();

        int[] res;

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        int n = map.size(),i = 0;
        Node[] nodes = new Node[n];
        Set<Map.Entry<Integer,Integer>> entry = map.entrySet();

        for (Map.Entry<Integer,Integer> entry1 : entry) {
            nodes[i++] = new Node(entry1.getKey(),entry1.getValue());
        }

        quickSort(nodes,0,n-1);

        res = new int[k];
        for (int j = n-1,l=0; j >= n-k; j--,l++) {
            res[l] = nodes[j].key;
        }

        return res;
    }

    public void quickSort(Node[] nodes,int low,int high){
        if (low >= high) {
            return;
        }

        int i = low,j = high;
        Node temp;

        while (i < j) {
            while (i < j && nodes[i].value <= nodes[j].value) {
                j--;
            }
            if (i < j) {
                temp = nodes[i];
                nodes[i] = nodes[j];
                nodes[j] = temp;
                i++;
            }
            while (i < j && nodes[i].value <= nodes[j].value) {
                i++;
            }
            if (i < j) {
                temp = nodes[i];
                nodes[i] = nodes[j];
                nodes[j] = temp;
                j--;
            }
        }

        quickSort(nodes,low,i-1);
        quickSort(nodes,i+1,high);
    }*/
    //-----------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------
    //使用堆解决，时间可以缩短至O(N*log k)
    //小根堆：使用数组实现，count计算小根堆中的元素个数
    public int[] topKFrequent(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        Node[] heap = new Node[k];

        //计算不同的元素出现的次数
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }

        Set<Map.Entry<Integer,Integer>> entries = map.entrySet();
        //遍历次数值，若当前的最小堆没有达到最大个数，则直接插入最小堆中，若是插入后最小
        // 堆达到了最大个数，则调用调整函数使堆变成最小堆
        //若当前的最小堆达到最大个数，则比较堆顶（当前的最小堆的最小次数）与当前次数数组
        // 中的元素
        for (Map.Entry<Integer,Integer> node : entries){
            Node node1 = new Node(node.getKey(),node.getValue());
            if (count < k) {
                heap[count++] = node1;
                if (count == k) {
                    //从第一个非叶子节点开始调整堆为最小堆
                    //第一个非叶子节点也就是最后一个叶子节点的父节点
                    for (int i = k/2 - 1; i > -1; i--) {
                        shift(heap,i);
                    }
                }
            }else{
                if (heap[0].value < node1.value) {
                    heap[0] = node1;
                    shift(heap,0);
                }
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap[i].key;
        }
        return res;
    }

    //小根堆调整函数
    //从第一个非叶子节点到根节点，持续的调用调整函数就可以构造出来最小堆
    public void shift(Node[] heap,int loc){
        int i = loc,j = 2 * i + 1;
        Node temp;
        while (j < heap.length) {
            if (j + 1 < heap.length && heap[j+1].value < heap[j].value) {
                j++;
            }

            if (heap[i].value > heap[j].value) {
                temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
    }
}
class Node implements Comparable<Node>{
    int key;
    int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
