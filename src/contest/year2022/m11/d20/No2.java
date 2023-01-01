package contest.year2022.m11.d20;

import java.util.*;

public class No2 {
    //失败了，没有考虑到分支退化的情况，在分支退化情况下，应该要使用二分查找
    /*Set<Integer> set;
    Map<Integer,List<Integer>> map;
    int max,min;
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        int n = queries.size();
        set = new HashSet<>();
        map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>(n);

        for (Integer i : queries) {
            List<Integer> list = map.get(i);
            if (list == null) {
                list = new ArrayList<>();
                if (set.contains(i)) {
                    list.add(i);
                    list.add(i);
                } else {
                    find(root, i);
                    list.add(min);
                    list.add(max);
                }
            }

            res.add(list);
        }

        return res;
    }
    public void find(TreeNode node,int val){
        if (node == null) {
            max = -1;
            min = -1;
            return;
        }

        set.add(node.val);
        if (node.val == val) {
            max = val;
            min = val;
        } else if (node.val > val) {
            find(node.left, val);
            if (max != -1)
                max = Math.min(node.val, max);
            else
                max = node.val;
        } else {
            find(node.right,val);
            min = Math.max(node.val,min);
        }
    }*/

    //先将二叉树准换成有序序列，然后进行二分查找，成功了，77ms
    List<Integer> list;
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        dfs(root);

        for (Integer i : queries) {
            int loc = find(i);
            List<Integer> temp = new ArrayList<>();
            if (list.get(loc).equals(i)) {
                temp.add(i);
                temp.add(i);
            } else{
                if (list.get(loc) < i) {
                    temp.add(list.get(loc));
                    temp.add(-1);
                } else {
                    if(loc == 0)
                        temp.add(-1);
                    else temp.add(list.get(loc-1));
                    temp.add(list.get(loc));
                }
            }
            res.add(temp);
        }

        return res;
    }
    public void dfs(TreeNode node){
        if(node == null)
            return;
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }
    //二分查找，如果没有相等的，就找到第一个大于元素的值
    public int find(int val){
        int l = 0,r = list.size()-1;
        while (l < r) {
            int c = l + ((r-l) >> 1);
            if(list.get(c) > val)
                r = c;
            else if(list.get(c) < val)
                l = c + 1;
            else
                return c;
        }
        return l;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}