package review.搜索和排序;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class No4 {
    /**
     *      前k个高频元素
     */

    /*Map<Integer,Integer> map = new HashMap<>();
    int[][] temp;
    int[] res;
    public int[] topKFrequent(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                count = 0;
            }
            map.put(nums[i],count+1);
        }

        temp = new int[k][2];
        res = new int[k];
        int loc = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> node = iterator.next();
            if (loc < k) {
                temp[loc][0] = node.getKey();
                temp[loc][1] = node.getValue();
                update(loc);
                loc++;
            }else{
                if (node.getValue() > temp[0][1]) {
                    temp[0][0] = node.getKey();
                    temp[0][1] = node.getValue();
                    shift(0,k);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = temp[i][0];
        }

        return res;
    }

    public void shift(int start,int end){
        int i = start,j = 2*i+1;
        while (j < end) {
            if (j < end - 1 && temp[j + 1][1] < temp[j][1]) {
                j++;
            }
            if (temp[i][1] > temp[j][1]) {
                int[] change = temp[i];
                temp[i] = temp[j];
                temp[j] = change;
                i = j;
                j = 2*i+1;
            } else {
                break;
            }
        }
    }

    public void update(int loc){
        int parent = (loc-1)/2,child = loc;
        while (child > 0) {
            if (temp[child][1] < temp[parent][1]) {
                int[] change = temp[child];
                temp[child] = temp[parent];
                temp[parent] = change;
                child = parent;
                parent = (child-1)/2;
            }else
                break;
        }
    }*/

    //使用快速排序，分治来寻找前k个高频元素
    Map<Integer,Integer> map;
    int k;
    public int[] topKFrequent(int[] nums, int k){
        map = new HashMap<>();
        this.k = k;

        for (int i = 0; i < nums.length; i++) {
            Integer num = map.get(nums[i]);
            if (num == null) {
                num = 0;
            }
            num++;
            map.put(nums[i],num);
        }
        int[][] temp = new int[map.size()][2];
        int loc = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> node = iterator.next();
            temp[loc][0] = node.getKey();
            temp[loc++][1] = node.getValue();
        }

        search(temp,0,temp.length-1);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = temp[i][0];
        }

        return res;
    }

    public void search(int[][] nums,int start,int end){
        int i = start,j = end;
        while (i < j) {
            while (i < j && nums[i][1] > nums[j][1]) {
                j--;
            }
            if (i < j) {
                int[] temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i][1] > nums[j][1]) {
                i++;
            }
            if (i < j) {
                int[] temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if (i + 1 == k) {
            return;
        } else if (i + 1 < k) {
            search(nums,i+1,end);
        }else
            search(nums,start,i-1);
    }
}
