package contest.year2022.oj.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No4 {
    /*public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        int[] nums = new int[s.length],temp;
        Map<Integer,Integer> map = new HashMap<>(),rank = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        Integer[] array = new Integer[map.size()];
        map.keySet().toArray(array);
        temp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            update(array,i);
        }
        for (int i = array.length-1; i > -1; i--) {
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            shift(array,0,i);
        }
        int c = 1;
        for (int i = array.length-1; i > -1; i--) {
            rank.put(array[i],c);
            c += map.get(array[i]);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            sb.append(rank.get(nums[i])).append(" ");
        }

        System.out.println(sb);
    }
    static void shift(Integer[] nums,int loc,int n){
        int i = loc,j = (i<<1)+1;
        while(j < n){
            if(j < n-1 && nums[j] < nums[j+1]){
                j++;
            }

            if(nums[i] < nums[j]){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i = j;
                j = (i<<1)+1;
            }else
                break;
        }
    }
    static void update(Integer[] nums,int loc){
        int i = loc,p = (i-1)>>1;
        while(i > 0){
            if(nums[i] > nums[p]){
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                i = p;
                p = (i-1)>>1;
            }else
                break;
        }
    }*/


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        int[] nums = new int[s.length],temp = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
            temp[i] = nums[i];
        }

        Arrays.sort(temp);
        Map<Integer,Integer> rank = new HashMap<>();
        int c = 1;
        for (int i = temp.length-1; i > -1; i--) {
            if(i == temp.length-1 || i < temp.length-1 && temp[i] != temp[i+1])
                rank.put(temp[i],c);
            c++;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            sb.append(rank.get(nums[i])).append(" ");
        }

        System.out.println(sb);
    }
}
