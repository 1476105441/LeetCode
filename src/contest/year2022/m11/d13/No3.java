package contest.year2022.m11.d13;

import java.util.*;

public class No3 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //翻译一下，应该就是将每层交换到递增顺序所需的最少操作数目
    //出问题了，应该要每一层单独排序
    /*public int minimumOperations(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
                size--;
            }
        }
        Map<Integer,Integer> map = new HashMap<>();
        int n = list.size(),res = 0;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = list.get(i);
        }
        Arrays.sort(temp);
        for (int i = 0; i < n; i++) {
            map.put(temp[i],i);
        }

        boolean[] set = new boolean[n];
        for (int i = 0; i < n; i++) {
            int loc,j = i,c = 0;
            while (!set[j] && (loc = map.get(list.get(j))) != j) {
                set[j] = true;
                j = loc;
                c++;
            }
            if(c > 0)
                res += c-1;
        }

        return res;
    }*/


    //成功，但是写的很复杂
    /*public int minimumOperations(TreeNode root) {
        //使用二维数组存放树节点的值，每一层的元素单独用一个list存放
        List<List<Integer>> total = new ArrayList<>();
        //bfs使用的队列
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
                size--;
            }
            total.add(list);
        }
        Map<Integer,Integer> map = new HashMap<>();
        int n = total.size(),res = 0;
        //每一层要单独排序，使用temp集合存放排序后的数组
        List<int[]> temp = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            //将每一层的数组单独存放起来
            int m = total.get(i).size();
            int[] nums = new int[m];
            for (int j = 0; j < m; j++) {
                nums[j] = total.get(i).get(j);
            }
            //单独排序
            Arrays.sort(nums);
            temp.add(nums);
        }

        //存储每个层次的元素应该在的位置
        for (int i = 0; i < n; i++) {
            int[] ints = temp.get(i);
            int m = ints.length;
            for (int j = 0; j < m; j++) {
                map.put(ints[j],j);
            }
        }

        for (int i = 0; i < n; i++) {
            List<Integer> list = total.get(i);
            int m = list.size();
            boolean[] set = new boolean[m];
            for (int j = 0; j < m; j++) {
                int loc,k = j,c = 0;
                while (!set[k] && (loc = map.get(list.get(k))) != k) {
                    set[k] = true;
                    k = loc;
                    c++;
                }
                if(c > 0)
                    res += c-1;
            }
        }

        return res;
    }*/


    //优化代码，在循环中解决，并没有优化到，83ms
    /*public int minimumOperations(TreeNode root){
        int res = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        Map<Integer,Integer> map = new HashMap<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> array = new ArrayList<>(),temp = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    array.add(node.val);
                    temp.add(node.val);
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
                size--;
            }
            Collections.sort(temp);
            for (int i = 0; i < temp.size(); i++) {
                map.put(temp.get(i),i);
            }
            for (int i = 0; i < array.size(); i++) {
                int j = i,k;
                while ((k = map.get(array.get(j))) != j) {
                    Integer t = array.get(j);
                    array.set(j,array.get(k));
                    array.set(k,t);
                    res++;
                }
            }
        }

        return res;
    }*/


    //题解的答案，使用数组，提升速度，击败了百分之90多
    /*public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int ans = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] arr = new int[size], temp = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                arr[i] = temp[i] = cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            Map<Integer, Integer> map = new HashMap<>();
            Arrays.sort(temp);
            for (int i = 0; i < arr.length; i++) map.put(temp[i], i);
            for (int i = 0; i < arr.length; i++) {
                while (arr[i] != temp[i]) {
                    int j = map.get(arr[i]);
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                    ans++;
                }
            }
        }
        return ans;
    }*/


    //重新自己写一遍使用数组的解法
    public int minimumOperations(TreeNode root){
        int res = 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        //使用map存储对应值应该在当前层的位置
        //Map<Integer,Integer> map = new HashMap<>(); //放在这个位置，49ms
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            Map<Integer,Integer> map = new HashMap<>();
            int[] array = new int[size],temp = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                array[i] = temp[i] = node.val;
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            Arrays.sort(temp);
            for (int i = 0; i < size; i++) {
                map.put(temp[i],i);
            }
            //遍历原数组，计算结果
            for (int i = 0; i < size; i++) {
                //当前位置和应该出现的位置不同时，要开始走环路
                int k;
                while ((k = map.get(array[i])) != i) {
                    //把当前元素交换到它应该存在的地方去
                    int t = array[i];
                    array[i] = array[k];
                    array[k] = t;
                    res++;
                }
            }
        }

        return res;
    }
}