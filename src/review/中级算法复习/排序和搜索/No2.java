package review.中级算法复习.排序和搜索;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class No2 {
    //          前k个高频元素

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int i = 0,n = map.size();
        Node[] temp = new Node[k];
        int[] res = new int[k];

        Set<Map.Entry<Integer, Integer>> set = map.entrySet();

        /**
         *  下面写法是使用最小堆进行解题，避免了不必要的排序，因为之前的排序是将整个数组的所有元素都
         * 排一遍，再从中选出前k个大的元素，而使用最小堆的思想是，直接使用元素数量为k的数组存放k个元
         * 素，将这个数组调整为最小堆，即第一个元素是当前数组中的最小元素，后面就不需要再进行排序了
         * 与最小元素比较，进行替换即可
         */
        for (Map.Entry<Integer, Integer> node : set) {
            Node node1 = new Node(node.getKey(),node.getValue());
            if (i < k) {
                temp[i++] = node1;
                if (i == k) {
                    mergeSort(temp);
                }
            } else{
                if (temp[0].value < node1.value) {
                    temp[0] = node1;
                    shift(temp,0,k);
                }
            }
        }

        for (i = 0; i < k; i++) {
            res[i] = temp[i].key;
        }

        return res;
    }

    public void quickSort(Node[] nums,int low,int high){
        if (low >= high) {
            return;
        }

        int i = low,j = high;
        Node temp;

        //由于要找前k大的，所以这里的判断逻辑要反过来
        while (i < j) {
            while (i < j && nums[i].value > nums[j].value) {
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i].value > nums[j].value) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        quickSort(nums,low,i-1);
        quickSort(nums,i+1,high);
    }

    //堆排序的调整算法
    public void shift(Node[] nums,int n,int num){
        Node temp;
        int i = n,j = 2 * i + 1;
        while (j < num) {
            if (j + 1 < num && nums[j].value > nums[j + 1].value) {
                j++;
            }

            if (nums[i].value > nums[j].value) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = 2 * i + 1;
            }else{
                break;
            }
        }
    }

    public void mergeSort(Node[] nums){
        int n = nums.length;
        Node temp;

        for (int i = (n-2)/2 ; i > -1; i--) {
            shift(nums,i,n);
        }

        /*for (int i = n-1; i > -1; i--) {
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            shift(nums,0,i);
        }*/
    }
}

class Node{
    public int key;
    public int value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
